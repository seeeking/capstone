package capstone;

public class Pic {
	// can be accessed by id and name;
	private String id;
	private String name;
	// the file stored in picpath and txtpath;
	private String picpath;
	private String txtpath;
	
	public String getId() {
		return id;
	}
	
	public void setId(String s){
		this.id = s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s){
		this.name = s;
	}
	
	public String getPicpath() {
		return picpath;
	}
	
	public void setPicpath(String s){
		this.picpath = s;
	}
	
	public String getTxtpath() {
		return txtpath;
	}
	
	public void setTxtpath(String s){
		this.txtpath = s;
	}
	
    @Override
    // ideally, maybe name + description or sth like that
    public String toString() {
        return id + name;
    }
}
