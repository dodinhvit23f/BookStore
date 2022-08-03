package com.api.dao;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.springframework.stereotype.Repository;

import com.api.config.ApiConstantsConfiguration;
import com.api.entity.Image;
import com.base.BaseDataAccess;

@Repository
public class BookImageDAO extends BaseDataAccess<Image> implements BookImageDAOI<Image>{

	@Override
	public void addImages(List<Image> images) throws EntityExistsException, QueryTimeoutException {
		images.forEach(image -> {
			this.insert(image);
		});
	}

	@Override
	public void deleteImage(String imageName) throws EntityNotFoundException, QueryTimeoutException {
		Image image = findByName(imageName);
		
		File file = Path.of(ApiConstantsConfiguration.IMAGE_PATH, image.getUrl()).toFile();
		if(file.exists()) {
			file.delete();
		}
		this.manager.remove(image);
	}

	@Override
	public Image findByName(String name) throws NoResultException, QueryTimeoutException {
		Query query = this.manager.createQuery("SELECT i FROM Image i WHERE i.url LIKE :name", Image.class)
		.setParameter("name", name);
		if(query.getFirstResult() != 0) {
			throw new NoResultException();
		}
		
		Image iamge =  (Image) query.getSingleResult();
		return iamge;
	}

}
