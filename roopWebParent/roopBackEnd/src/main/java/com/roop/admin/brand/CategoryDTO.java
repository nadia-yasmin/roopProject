package com.roop.admin.brand;

public class CategoryDTO {
private Integer Id;
private String name;

public CategoryDTO() {
	//super();
	// TODO Auto-generated constructor stub
}
public CategoryDTO(Integer id, String name) {
	//super();
	Id = id;
	this.name = name;
}
public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
