package com.waylau.rest.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("files")
public class FileResource {
	
	private static final String filepath = "D:/测试文档/小柳哥.txt";
	private static final String serverLocation = "D:/测试文档/";
	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    @GET
    @Path("download")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile() throws UnsupportedEncodingException {

    	File file = new File(filepath);
		if (file.isFile() && file.exists()) {
			String mt = new MimetypesFileTypeMap().getContentType(file);
			String fileName = file.getName();
			
			//处理文件名称编码
			fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1");
			
			return Response
					.ok(file, mt)
					.header("Content-disposition",
							"attachment;filename=" + fileName)
					.header("ragma", "No-cache")
					.header("Cache-Control", "no-cache").build();

		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("下载失败，未找到该文件").build();
		}
	}

    /**
     * 文件上传
     * 
     * @param fileInputStream
     * @param contentDispositionHeader
     * @return
     * @throws IOException 
     */
    @POST
    @Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) 
					throws IOException {
    	
		String fileName = contentDispositionHeader.getFileName();
		//处理文件名称编码
		//fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
		
    	File file = new File(serverLocation + fileName); 
		File parent = file.getParentFile(); 
		//判断目录是否存在，不在创建 
		if(parent!=null&&!parent.exists()){ 
			parent.mkdirs(); 
		} 
		file.createNewFile(); 
		
		OutputStream outpuStream = new FileOutputStream(file);
		int read = 0;
		byte[] bytes = new byte[1024];
 
		while ((read = fileInputStream.read(bytes)) != -1) {
			outpuStream.write(bytes, 0, read);
		}

		outpuStream.flush();
		outpuStream.close();

		fileInputStream.close();

    	return Response.status(Response.Status.OK)
				.entity("Upload Success!").build();
    }
}
