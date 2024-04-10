package poly.store.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import poly.store.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	ServletContext app;

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath("/assets/images/") + folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File saveFile = new File(dir, name);
			file.transferTo(saveFile);
			System.out.println(saveFile.getAbsolutePath());
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


}
