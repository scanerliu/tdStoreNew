package com.tiandu.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tiandu.common.utils.ImageZipUtil;
import com.tiandu.common.utils.OSTypeUtil;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.service.TdProductAttachmentService;

/**
 * 商品图片上传
 * @author Max
 *
 */
@Controller
@RequestMapping("/product/upload")
public class ProductUploadController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	// 独立图片上传
	@RequestMapping(value = "/singleFile", method = RequestMethod.POST)
	@ResponseBody
	public void upload( MultipartFile file, String type, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		if(file == null || type == null || type.equals("")){
			logger.error("文件上传失败，原因: " + "上传文件为空或上传类型为空");
			res.put("code", "0");
			res.put("msg", "上传失败。");
		}else{
			try {
				// 获取扩展名
				String originalFilename = file.getOriginalFilename();
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				// 保存的文件名
				String savedFileName = UUID.randomUUID().toString() + suffix;
				// 保存的文件
				File savedFile = new File(this.getFolder(type, request) + "/" + savedFileName);
				
				FileCopyUtils.copy(file.getBytes(), savedFile);
				//图片压缩
				ImageZipUtil.zipImageFile(savedFile,savedFile,800,0,0.7f);
				
				res.put("code", "1");
				res.put("msg", "上传成功。");
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1; // 0表示一月 
				int day = now.get(Calendar.DAY_OF_MONTH);
				
				String imgUrl = "/static/imgs/" + type + "/" + year + "/" + month + "/" + day + "/" + savedFileName;
				TdProductAttachment attachment = new TdProductAttachment();
				attachment.setAttachment(imgUrl);
				attachment.setFilename(savedFileName);
				tdProductAttachmentService.save(attachment);
				
				res.put("atts", attachment.getId().toString());
				res.put("savedFile", imgUrl);
			} catch (IOException e) {
				logger.error("文件上传失败，原因: " + e);
				e.printStackTrace();
				res.put("code", "0");
				res.put("msg", "上传失败。");
			}
		}
		WebUtils.responseJson(response, res);
	}
	
	private String getFolder(String type, HttpServletRequest request){
		// 获取操作系统
		String os = OSTypeUtil.getOSType();
		// 项目根路径
		String projectRoot = "";
		if(os.equals("WINDOWS")){
			// 请设置本机的项目开发环境绝对路径
			//projectRoot = "D:\\gaolu\\gitProject\\tdStore\\src\\main\\webapp\\static\\imgs";
		}else if(os.equals("LINUX")){
			//projectRoot = "/mnt/root/tdStore/imgs";
		}
		projectRoot = request.getServletContext().getRealPath("/") + "static/imgs";
		
		// 图片保存根路径
		String imgRoot = projectRoot + "/" + type;
		// 创建保存目录
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // 0表示一月 
		int day = now.get(Calendar.DAY_OF_MONTH);
		File dayDir = new File(imgRoot + "/" + year + "/" + month + "/" + day);
		if (!dayDir.exists()) {   
			dayDir.mkdirs();   
		}
		return dayDir.getPath();
	}
}
