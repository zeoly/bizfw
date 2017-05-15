package com.thinkequip.bizfw.document.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkequip.bizfw.base.BaseAction;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.FileUtils;
import com.thinkequip.bizfw.document.model.Document;
import com.thinkequip.bizfw.document.service.DocumentService;
import com.thinkequip.bizfw.po.model.People;

@Controller
@RequestMapping("/fileAction")
public class FileAction extends BaseAction {

	@Autowired
	private DocumentService documentService;

	@ResponseBody
	@RequestMapping("/getContentOfFolder.do")
	public List<Document> getContentOfFolder(HttpServletRequest request, String folderId) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Document folder = documentService.queryById(folderId);
		folder.update(people.getCode());
		List<Document> list = documentService.getContentOfFolder(folder);
		return list;
	}

	@ResponseBody
	@RequestMapping("/addFile.do")
	public String addFile(HttpServletRequest request, MultipartFile file, String folderId) throws BizfwServiceException {
		try {
			String fileName = file.getOriginalFilename();
			String url = FileUtils.getStorageUrl(fileName);
			String filePath = FileUtils.getLocalStorage() + url;
			file.transferTo(new File(filePath));

			People people = getLoginPeople(request);
			Document document = new Document(people.getCode());
			document.setOwnerDocumentId(folderId);
			document.setName(fileName);
			document.setUrl(url);
			document.setSize(file.getSize());
			// TODO md5
			documentService.addFile(document);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/updateFile.do")
	public String updateFile(HttpServletRequest request, MultipartFile file, String fileId)
			throws BizfwServiceException {
		try {
			String fileName = file.getOriginalFilename();
			String url = FileUtils.getStorageUrl(fileName);
			String filePath = FileUtils.getLocalStorage() + url;
			file.transferTo(new File(filePath));

			People people = getLoginPeople(request);
			Document document = new Document(people.getCode());
			document.setOwnerDocumentId(fileId);
			document.setName(fileName);
			document.setUrl(url);
			document.setSize(file.getSize());
			documentService.updateFile(document);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyFile.do")
	public String modifyFile(HttpServletRequest request, Document document) throws BizfwServiceException {
		People people = getLoginPeople(request);
		document.update(people.getCode());
		documentService.modifyFile(document);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteFile.do")
	public String deleteFile(HttpServletRequest request, String documentId) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Document file = documentService.queryById(documentId);
		file.update(people.getCode());
		documentService.deleteFile(file);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getHistoryFiles.do")
	public List<Document> getHistoryFiles(String documentId) throws BizfwServiceException {
		Document file = documentService.queryById(documentId);
		List<Document> list = documentService.getHistoryFileList(file);
		return list;
	}

	@ResponseBody
	@RequestMapping("/getAuthFolderTree.do")
	public Document getAuthFolderTree(HttpServletRequest request) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Document folder = documentService.getAuthFolderTree(people);
		return folder;
	}

	@ResponseBody
	@RequestMapping("/downloadFile.do")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileId)
			throws BizfwServiceException, IOException {
		try {
			Document document = documentService.queryById(fileId);
			String name = FileUtils.getLocalStorage() + document.getUrl();

			File file = new File(name);
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(document.getName().getBytes("utf-8"), "iso8859-1"));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			InputStream in = new FileInputStream(name);
			OutputStream out = response.getOutputStream();
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
