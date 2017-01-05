package cn.bigdb.smartscreen.utils;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class VideoUtils {
	public static void main(String[] args) {  
		  
		String videoPath = "d:\\wanda_whw.mp4";
	    String result =    processFLV(videoPath);  
	     
	     
	    PatternCompiler compiler =new Perl5Compiler();  
	    try {  
	        String regexDuration ="Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";  
	        String regexVideo ="Video: (.*?), (.*?), (.*?)[,\\s]";  
	        String regexAudio ="Audio: (\\w*), (\\d*) Hz";  
	     
	        Pattern patternDuration = compiler.compile(regexDuration,Perl5Compiler.CASE_INSENSITIVE_MASK);  
	        PatternMatcher matcherDuration = new Perl5Matcher();  
	        if(matcherDuration.contains(result, patternDuration)){  
	            MatchResult re = matcherDuration.getMatch();  
	  
	            System.out.println("提取出播放时间  ===" +re.group(1));  
	            System.out.println("开始时间        =====" +re.group(2));  
	            System.out.println("bitrate 码率 单位 kb==" +re.group(3));  
	        }  
	         
	        Pattern patternVideo = compiler.compile(regexVideo,Perl5Compiler.CASE_INSENSITIVE_MASK);  
	        PatternMatcher matcherVideo = new Perl5Matcher();  
	         
	        if(matcherVideo.contains(result, patternVideo)){  
	            MatchResult re = matcherVideo.getMatch();  
	            System.out.println("编码格式  ===" +re.group(1));  
	            System.out.println("视频格式 ===" +re.group(2));  
	            System.out.println(" 分辨率  == =" +re.group(3));  
	        }  
	         
	        Pattern patternAudio = compiler.compile(regexAudio,Perl5Compiler.CASE_INSENSITIVE_MASK);  
	        PatternMatcher matcherAudio = new Perl5Matcher();  
	         
	        if(matcherAudio.contains(result, patternAudio)){  
	            MatchResult re = matcherAudio.getMatch();  
	            System.out.println("音频编码             ===" +re.group(1));  
	            System.out.println("音频采样频率  ===" +re.group(2));  
	        }  
	        System.out.println(getVideoPicPath(videoPath));
	        mp42flv();
	    } catch (MalformedPatternException e) {  
	        e.printStackTrace();  
	    }  
	  
	    }  
	     
	  
	//  ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
	    private static String processFLV(String inputPath) {  
	/* 
	      if (!checkfile(inputPath)){ 
	          _log.warn(inputPath+" is not file"); 
	          return false; 
	         } 
	*/  
	        List<String> commend=new java.util.ArrayList<String>();  
	         
	        //commend.add("D:\\software\\ffmpeg\\bin\\ffmpeg.exe");//可以设置环境变量从而省去这行  
	        commend.add("ffmpeg");  
	        commend.add("-i");  
	        commend.add(inputPath);  
	        
	        try {  
	  
	            ProcessBuilder builder = new ProcessBuilder();  
	            builder.command(commend);  
	            builder.redirectErrorStream(true);  
	            Process p= builder.start();  
	  
	           //1. start  
	            BufferedReader buf = null; // 保存ffmpeg的输出结果流  
	            String line = null;  
	          //read the standard output  
	  
	            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));  
	             
	            StringBuffer sb= new StringBuffer();  
	            while ((line = buf.readLine()) != null) {  
	             System.out.println(line);  
	             sb.append(line);  
	             continue;  
	                 }  
	            int ret = p.waitFor();//这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行  
	            //1. end  
	            return sb.toString();  
	        } catch (Exception e) {  
	        	e.printStackTrace();
//	            System.out.println(e);  
	            return null;  
	        }  
	    }  
	    
	    public static void mp42flv(){
	    	File source = new File("d:\\wanda_whw.mp4");
	    	File target = new File("d:\\wanda_whw.flv");
	    	AudioAttributes audio = new AudioAttributes();
	    	audio.setCodec("libmp3lame");
	    	audio.setBitRate(new Integer(64000));
	    	audio.setChannels(new Integer(1));
	    	audio.setSamplingRate(new Integer(22050));
	    	VideoAttributes video = new VideoAttributes();
	    	video.setCodec("flv");
	    	video.setBitRate(new Integer(160000));
	    	video.setFrameRate(new Integer(15));
//	    	video.setSize(new VideoSize(400, 300));
	    	EncodingAttributes attrs = new EncodingAttributes();
	    	attrs.setFormat("flv");
	    	attrs.setAudioAttributes(audio);
	    	attrs.setVideoAttributes(video);
	    	
	    	StringBuilder shellResult = null;
	    	shellResult = new StringBuilder();
	    	
	    	Encoder encoder = new Encoder();
	    	try {
				encoder.encode(source, target, attrs);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InputFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncoderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public static String getVideoPicPath(String sourcePath){
	    	String path = "E:\\a.jpg";
	    	 // 创建一个List集合来保存从视频中截取图片的命令
	        List<String> cutpic = new ArrayList<String>();
	        cutpic.add("ffmpeg");
	        cutpic.add("-i");
	        cutpic.add(sourcePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
	        cutpic.add("-y");
	        cutpic.add("-f");
	        cutpic.add("image2");
	        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
	        cutpic.add("17"); // 添加起始时间为第17秒
	        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
	        cutpic.add("0.1"); // 添加持续时间为1毫秒
//	        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
//	        cutpic.add("800*280"); // 添加截取的图片大小为350*240
	        cutpic.add(path); // 添加截取的图片的保存路径
	        
	        ProcessBuilder builder = new ProcessBuilder();  
            
            try {
            	builder.command(cutpic);  
                builder.redirectErrorStream(true);
				builder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            return path;
	    }
	    
	    
	    /**
	     * 视频转码
	     * @param ffmpegPath    转码工具的存放路径
	     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
	     * @param codcFilePath    格式转换后的的文件保存路径
	     * @param mediaPicPath    截图保存路径
	     * @return
	     * @throws Exception
	     */
	    public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
	            String mediaPicPath) throws Exception {
	        // 创建一个List集合来保存转换视频文件为flv格式的命令
	        List<String> convert = new ArrayList<String>();
	        convert.add(ffmpegPath); // 添加转换工具路径
	        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
	        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
	        convert.add("-qscale");     //指定转换的质量
	        convert.add("6");
	        convert.add("-ab");        //设置音频码率
	        convert.add("64");
	        convert.add("-ac");        //设置声道数
	        convert.add("2");
	        convert.add("-ar");        //设置声音的采样频率
	        convert.add("22050");
	        convert.add("-r");        //设置帧频
	        convert.add("24");
	        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
	        convert.add(codcFilePath);

	        // 创建一个List集合来保存从视频中截取图片的命令
	        List<String> cutpic = new ArrayList<String>();
	        cutpic.add(ffmpegPath);
	        cutpic.add("-i");
	        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
	        cutpic.add("-y");
	        cutpic.add("-f");
	        cutpic.add("image2");
	        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
	        cutpic.add("17"); // 添加起始时间为第17秒
	        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
	        cutpic.add("0.001"); // 添加持续时间为1毫秒
	        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
	        cutpic.add("800*280"); // 添加截取的图片大小为350*240
	        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

	        boolean mark = true;
	        ProcessBuilder builder = new ProcessBuilder();
	        try {
	            builder.command(convert);
	            builder.redirectErrorStream(true);
	            builder.start();
	            
	            builder.command(cutpic);
	            builder.redirectErrorStream(true);
	            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
	            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
	            builder.start();
	        } catch (Exception e) {
	            mark = false;
	            System.out.println(e);
	            e.printStackTrace();
	        }
	        return mark;
	    }
}
