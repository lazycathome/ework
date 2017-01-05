<%@ codepage=65001%>
<%
function getStudents(typeId, num)
	dim str, subject, url, imageUrl, summary
	dim conn, rs, sql

	set conn = server.createObject("ADODB.Connection")
	conn.Open application("connWebsite")
	sql = "SELECT top "&num&" * FROM ARTICLES WHERE COLUMNID="&columnId&" ORDER BY REGTIME DESC"
	response.write(sql)
	set rs = conn.execute(sql)
	if not (rs.eof or rs.bof) Then
		do while not rs.eof
			subject = rs("SUBJECT")
			url = "news.asp?id="&rs("ID")
			imageUrl = rs("DESCRIPTION")
			summary = left(rs("CONTENT"), 100)
			if (typeId = 99) then
				str = str&createHTML(url, imageUrl, subject)
			else
				str = str&createHTML2(summary, url, imageUrl, subject)
			end if
			rs.movenext
		loop

	End if

	rs.close
	set rs = nothing

	conn.close
	set conn = Nothing

	getStudents = str
end function

Private Function createHTML(url, imageUrl, subject)
	Dim str

	str = "<li>"
    str = str&"<div align=""center"">"
    str = str&"<a href="""&url&""" target=""_blank""> <img src="""&imageUrl&""" width=""240"" height=""208"" /></a>"
    str = str&"</div>"
    str = str&"<div align=""center"">"
    str = str&"<a href="""&url&""" target=""_blank"">"&subject&"</a>"
    str = str&"</div>"
    str = str&"</li>"
	createHTML = str
End Function

Private Function createHTML2(summary, url, imageUrl, subject)
	Dim str

	str = "<div class=""con_xq_left""><a href="""&url&""" target=""_blank""><img src="""&imageUrl&""" width=""398"" height=""253"" /></a></div>"
    str = str&"<h4><a href="""&url&""" target=""_blank"">"&subject&"</a></h4>"
    str = str&"<p><a href="""&url&""" target=""_blank"">"&summary&"</a></p>"
 	str = str&"<div class=""clear""></div>"
  	str = str&"</div>"

	createHTML2 = str
End Function
%>