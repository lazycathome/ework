<%@ codepage=65001%>
<%
' ------------------------------------------------------- '
' 公用的常量
' ------------------------------------------------------- '
const ASSD_ERR_NoError = 0
const ASSD_ERR_UserNotExisting = 1
const ASSD_ERR_InvalidPassword = 2

const adOpenForwardOnly = 1
const adOpenKeyset = 2
const adOpenDynamic = 3
const adOpenStatic = 4

const adLockReadOnly = 1
const adLockPessimistic = 2
const adLockOptimistic = 3
const adLockBatchOptimistic = 4

const adCmdText = 1
const adCmdTable = 2
const adCmdStoredProc = 4
const adCmdUnknown = 8

const ASPRO_PageSize = 20
const ASPROV_PageSize = 50
const ASNEWS_PageSize = 20

Const pageSize = 20

' ------------------------------------------------------- '
' 公用的函数
' ------------------------------------------------------- '

function sendMail(subject, body, mailto)
dim mail, rlt
dim conn, rs

set conn = server.createObject("ADODB.Connection")
conn.Open application("connWebsite")

set rs = conn.execute("SELECT * FROM SYSMAIL")
if not (rs.eof or rs.bof) then
	Set mail = server.createObject("JMail.Message")

    mail.Logging = true
    mail.silent = true

    mail.from = rs("EMAIL")
    mail.fromName = rs("NAME")
    mail.mailServerUserName = rs("USER")
    mail.mailServerPassWord = rs("PWD")
    
    mail.addRecipient mailto

	mail.charset = "GB2312"
	
    mail.subject = subject

    mail.body = body

	rlt = mail.send(rs("SERVER"))

	set mail = nothing
end if

rs.close
set rs = nothing

conn.close
set conn = nothing

sendMail = rlt
end Function

Function checkNull(str)
	If IsNull(str) Or str = "" Then
		str = 0
	End If
	checkNull = str
End Function

'获取新闻频道首页的新闻展示
'columnId	频道栏目id
'num		获取的条目数
Function getNews(columnId, num)
	dim str, subject, url
	dim conn, rs

	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")

	columnId = checkNull(columnId)
	set rs = conn.execute("SELECT top "&num&" * FROM ARTICLES WHERE COLUMNID="&columnId&" ORDER BY ISTOP,ISHOT,REGTIME DESC")
	if not (rs.eof or rs.bof) Then
		do while not rs.eof
			subject = rs("SUBJECT")
			url = "news.asp?id="&rs("ID")
			str = str&createHTML(subject, url, "", "")
			rs.movenext
		loop

	End if

	rs.close
	set rs = nothing

	conn.close
	set conn = Nothing

	getNews = str

End Function

'创建各个频道首页展示标题的html
'subject 主题
'url	链接地址
Private Function createHTML(subject, url, regtime, author)
	Dim str
''	str = "<p><a title="""&subject&""" href="""&url&""" target=""_blank"">"&subject&"</a></p>"
	str = ".<a title="""&subject&""" href="""&url&""" target=""_blank"">"&subject&"      "&regtime&"   "&author&"</a><br />"
	createHTML = str
End Function

pageInfo = ""

'获取课程库频道的新闻展示
'columnName	频道栏目名称
'curpage		获取的条目数
Function getAticleList(uri, typeId, curpage)
	dim str
	dim conn, rs

	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")
	columnName = checkNull(columnName)'

	if isnull(curpage) or curpage="" or int(curpage)<=0 then
		curpage = 1
	end if
	'sql = "SELECT COUNT(*) FROM ARTICLES WHERE COLUMNNAME in ("&columnName&")"
	sql = "SELECT COUNT(*) FROM ARTICLES WHERE DISPLAY=0 AND typeId="&typeId
	Set rs = Server.CreateObject("Adodb.RecordSet")
	rs.open sql,conn,2,1
	totalcount=rs(0)
	'response.write("totalcount:"&totalcount)
	rs.close
	if totalcount=0 then
	    getAticleList = ""

	else
		if int(curpage)=1 then
			'sql= "select top "& pageSize &" * from ARTICLES WHERE COLUMNNAME in ("&columnName&") ORDER BY REGTIME DESC"
			sql= "select top "& pageSize &" * from ARTICLES WHERE DISPLAY=0 AND typeId ="&typeId&" ORDER BY REGTIME DESC"
		else
			'sql= "select top "& pageSize &" * from ARTICLES where ID not in (select top "& (curpage-1)*pageSize &" ID from ARTICLES  WHERE COLUMNNAME in ("&columnName&") ORDER BY REGTIME DESC) "
			sql= "select top "& pageSize &" * from ARTICLES where ID not in (select top "& (curpage-1)*pageSize &" ID from ARTICLES  WHERE DISPLAY=0 AND typeId ="&typeId&" ORDER BY REGTIME DESC) "
		end if

		set rs = conn.execute(sql)
		if not (rs.eof or rs.bof) Then
			do while not rs.eof
				subject = rs("SUBJECT")
				url = "showArticle.asp?id="&rs("ID")
				regtime = rs("REGTIME")
				author = rs("AUTHOR")
				str = str&createHTML(subject, url, regtime, author)

				rs.movenext
			loop
		End if
		rs.close
		set rs = nothing

		conn.close
		set conn = Nothing

		getAticleList = str
	end if

	pageInfo = createPageInfo(uri, currpage, totalcount/pageSize)
End Function

Function createPageInfo(url, pageNo, pageCount)
	dim str
	str = "<p>"
	If pageNo = 1 then
		for i=0 to 5
			if i = pageNo then
				str = str&"<a href="&url&"?curpage="&i&" ><b>"&i&"</b></a>"
			else
				str = str&"<a href="&url&"?curpage="&i&" >"&i&"</a>"
			end if 
		Next
		str = str&"...<a href="&url&"?curpage="&pageCount&" >"&i&"</a>"
	Else
		str = str&"<a href="&url&"?curpage=1 >1</a>..."
		j = pageNo-2
		l = (pageNo + 2) 
		l = IIf(l>pageCount, pageCount, l)
		for i=j to l
			if i = pageNo then
				str = str&"<a href="&url&"?curpage="&i&" ><b>"&i&"</b></a>"
			else
				str = str&"<a href="&url&"?curpage="&i&" >"&i&"</a>"
			end if 
			
		Next
	End If

	str = str&"</p>"
	createPageInfo = str
End Function


Function getAticleList2Index(typeId, folderName, size)
	dim str
	dim conn, rs

	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")
	Set rs=Server.CreateObject("Adodb.RecordSet")
	
	sql= "select top "& size &" * from ARTICLES WHERE DISPLAY=0 AND typeId ="&typeId&" ORDER BY REGTIME DESC"
	'response.write(sql)
	rs.open sql,conn,0,1
	'response.write(rs.RecordCount)
	if not (rs.eof or rs.bof) Then
		do while not rs.eof
			subject = rs("SUBJECT")
			url = folderName&"/showArticle.asp?id="&rs("ID")
			str = str&createHTML2Index(subject, url)
			rs.movenext
		loop
	End if
	rs.close
	set rs = nothing

	conn.close
	set conn = Nothing

	getAticleList2Index = str
End Function

Function createHTML2Index(subject, url)
	Dim str
''	str = "<p><a title="""&subject&""" href="""&url&""" target=""_blank"">"&subject&"</a></p>"
	str = "<li>. <a title="""&subject&""" href="""&url&""" target=""_blank"">"&subject&"</a></li>"
	createHTML2Index = str

End Function

'获取课程库频道的新闻展示
'columnId	频道栏目id
'typeId 栏目类型id,文章为1，诊疗中心为2，课程库为3
Function getColumn(columnId, typeId, num)
	dim str, count
	dim conn, rs, sql

	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")

	columnId = checkNull(columnId)

	sql = "SELECT top "&num&" * FROM COLUMNS WHERE DISPLAY=0 "
	If Not checkNull(columnId) = 0 Then
		sql = sql&" AND PARENTID="&columnId
	End If 
	If Not checkNull(typeId) = 0 Then
		sql = sql&" AND TYPEID="&typeId
	End If 
	sql = sql&" ORDER BY REGTIME DESC"
	'response.write(sql)
	set rs = server.CreateObject("ADODB.recordset")
	rs.open sql,conn,0,1
	if not (rs.eof or rs.bof) Then
		do while not rs.eof
			subject = rs("COLUMNNAME")
			url = "news.asp?id="&rs("ID")
			'response.write(subject&"<br>")
			'response.write(url)

			str = str&createHTML(subject, url, "", "")
			rs.movenext
		loop

	End if

	rs.close
	set rs = nothing

	conn.close
	set conn = Nothing

	getColumn = str

End Function


Function replaceStr(str)
	replaceStr= replace(Trim(str),"'","")
End Function

'获取新闻内容'
Function getAticleContent(id)
	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")
	
	sql= "select * from ARTICLES WHERE ID="&id

	set rs = conn.execute(sql)
	if not (rs.eof or rs.bof) Then
		regtime = rs("REGTIME")
		subject = rs("SUBJECT")
		content = rs("CONTENT")
	Else
		response.Redirect("/")
		response.end
	End If

End Function

'获取ip地址
Function getUserIP()
	userIP = Request.ServerVariables("HTTP_X_FORWARDED_FOR") 
	If userIP = "" Then userIP = Request.ServerVariables("REMOTE_ADDR") 
	getUserIP = userIP
End Function

Function IIf(Condition, ValueIfTrue, ValueIfFalse)  
    If Condition Then  
        IIf = ValueIfTrue  
    Else  
        IIf = ValueIfFalse  
    End if  
End Function

%>
