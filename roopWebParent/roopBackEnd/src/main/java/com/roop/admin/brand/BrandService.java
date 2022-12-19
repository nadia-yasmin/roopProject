package com.roop.admin.brand;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
//import com.roop.admin.service.impl.IBrandService;
import com.roop.common.entity.Brand;
@Service
@Transactional 
public class BrandService{
	@Autowired private BrandRepository repo;
	public List<Brand> listAll(){
		Sort firstNameSorting =  Sort.by("name").ascending();
		List<Brand> listBrands = new ArrayList<>();
		repo.findAll(firstNameSorting).forEach(listBrands::add);
		return listBrands;
	}
	public Brand save(Brand brand) {
		return repo.save(brand);
	}
	public Brand get(Integer id) throws BrandNotFoundException{
		try {
			return repo.findById(id).get();
		} catch(NoSuchElementException ex){
			throw new BrandNotFoundException("Could not find any brand with Id: "+id);
			
		}
	}
	public void  delete(Integer id) throws BrandNotFoundException{
		Long countById= repo.countById(id);
		if(countById== null||countById==0) {
			throw new BrandNotFoundException("Could not find any brand with Id: "+id);
		}
		repo.deleteById(id);
	}
	
	
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew=(id==null||id==0);
		Brand brandByName= repo.findByName(name);
		if(isCreatingNew) {
			if(brandByName!=null) return "Duplicate";
		} else {
			if(brandByName!=null && brandByName.getId() !=id) {
				return "Duplicate";
		}
		}
		return "OK";
	}
}


