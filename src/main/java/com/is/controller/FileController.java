package com.is.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.is.entity.TUser;
import com.is.json.entty.FileUrlStatus;
import com.is.json.status.Status;

@Controller
public class FileController {
	
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@RequestMapping(value="/uploadfile",method=RequestMethod.POST)
	public @ResponseBody FileUrlStatus uploadNodeFile(@RequestParam("file") MultipartFile file,
            HttpSession session) {
		System.out.println("正在上传文件");
		String str = "fdafa";
//		str.lastIndexOf(ch)
//		TUser user = (TUser) session.getAttribute("user");
//		if(user == null) {
//			return new Status("fail", -1, "请先登陆");
//		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileName = //user.getAccount()
				 format.format(System.currentTimeMillis())
				+ file.getOriginalFilename();
		File dest = new File(this.uploadPath + "node/" + fileName);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new FileUrlStatus("fail", -1, "请先登陆");
		}
		FileUrlStatus status = new FileUrlStatus("success", 1, "上传成功");
		status.setUrl("http://localhost:5138/node/" + fileName);
		return status;
	}
	
	
	
}
