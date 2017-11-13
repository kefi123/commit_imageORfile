package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Image;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/servlet/imageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		commitImage(request,response);
	}
	private void commitImage(HttpServletRequest request, HttpServletResponse response){
//		����һ��ʵ���࣬����֮��洢��Ԫ�ص�ֵ
		Image image = new Image();
		//		�õ��ļ����ϴ�Ŀ¼��
//		��������Ҫ�ռ�һ�£���Ϊ���������ϴ�����ͼƬ���Ժ���Ҫ�ã�
//		������������ϴ������ļ�����ô��Ӧ�÷���web-inf�£������������ʣ��������ݵİ�ȫ
		
//		getServletContext()�������ڻ�������ģ�getRealPath()���ڵõ������ĵ�����·��
		String savePath = this.getServletContext().getRealPath( "/upload");
//		�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
		String tempPath = this.getServletContext().getRealPath( "/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if(!tmpFile.exists()){
			//������Ŀ¼
			tmpFile.mkdir();
		}
//		��Ϣ��ʾ
		String message = "";
		try{
//			ʹ��Apache�ļ��ϴ���������ļ��ϴ�����
//			1.����һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			���ù����Ļ�������С��ʹ�ó������и��죬Ĭ�ϻ�����10kb,������Ϊ100kb
			factory.setSizeThreshold(1024*100);
//			������ʱ�ļ��ı���Ŀ¼
			factory.setRepository(tmpFile);
//			2.����һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
//			���������������
			upload.setHeaderEncoding( "UTF-8");
//			3.�ж��ύ�����������Ƿ����ϴ���������
			if(!ServletFileUpload.isMultipartContent(request))
			{
//				���մ�ͳ��ʽ��ȡ����
				return ;
			}
//			�����ϴ������ļ��Ĵ�С�����ֵ
			upload.setFileSizeMax(1024*1024);
//			�����ϴ��ļ����������ֵ
			upload.setSizeMax(1024*1024*10);
//			4.ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list)
			{
				//���item������ͨ�����������
				if(item.isFormField()){
					//���ô�����ͨ������ķ���
					processFormField(item,image);
				}
				else{
					//���ô����ļ��ķ�����
					processUploadFile(item,image,savePath);
				}
			}
		}catch(Exception e){
			message = "�ļ��ϴ�ʧ�ܣ�";
			e.printStackTrace();
		}
//		����ʾ��Ϣ�ŵ�����������
		request.setAttribute( "message", message);
//		ת��ĳ��ҳ��
//		System.out.println(image.getName());
//		System.out.println(image.getPath());
		request.setAttribute("image", image);
		try {
			request.getRequestDispatcher("../image/display.jsp").forward(request, response);
		} catch (ServletException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
//	�����ϴ����ļ�
	private void processUploadFile(FileItem item,Image image,String savePath){
//		�õ��ļ�������
		String filename = item.getName();
//		�ҵ��ļ�����ǰ���.��λ��
		int index = filename.lastIndexOf( ".");
		String filetype = filename.substring(index+1, filename.length());
//		��ȡ�ļ��ĳ���
		long fileSize = item.getSize();
//		����ļ���Ϊ�գ��ļ�����Ϊ�գ���ôֱ�ӷ���
		if(filename.equals( "")&&fileSize==0){
			return;
		}
		
//		����һ�����File���֪ʶ
//		File��Ĳ����ǳ���·�������Դ��ڣ�Ҳ���Բ�����
//		mkdirs()���ڴ���Ŀ¼������в����ڵĸ�Ŀ¼����ôҲ�ᴴ����Ŀ¼
//		mkdir()���ڴ���Ŀ¼
		
//		�½���������ļ����ļ��� ���ļ��е���������������ʾ����ֹһ���ļ������й�����ļ����ļ�����UUID����������ֹ�ļ������ظ�
//		�ȵõ�����
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date1=sdf.format(date);
		
		File file = new File(savePath+"/"+date1);
		if(!file.exists())
		{
			file.mkdirs();
		}
		//�½��ļ����������ϴ��������ļ�
//		�ȴ���һ��UUID
		String uuid = UUID.randomUUID().toString();
		File uploadFile = new File(savePath+"/"+date1+"/"+uuid+"."+filetype);
		try{
			item.write(uploadFile);
//			�ĳ���Ӧ��������ip
			image.setPath("http://www.xhjweb.top/commit_image/upload/"+date1+"/"+uuid+"."+filetype);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	������ͨ��������
	private void processFormField(FileItem item,Image image){
//		��ȡ�ÿؼ���name
		String name = item.getFieldName();
		int name1 = 0;
		if(name.equals("name"))
			name1=1;
		
//		��ȡ�ÿؼ���ֵ
		try {
			String value = new String(item.getString("UTF-8"));
			switch(name1){
			case 1:
				image.setName(value);
				break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
