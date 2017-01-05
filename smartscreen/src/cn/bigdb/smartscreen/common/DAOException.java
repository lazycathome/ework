package cn.bigdb.smartscreen.common;

public class DAOException extends Exception {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1731220306271990312L;
	private String message="";
    public DAOException(){
        super();
    }
    public DAOException(String message){
        super(message);
        this.message=message;
    }
    public DAOException(Exception e){
        super(e);
        this.message=e.getMessage();
    }
    @Override
	public String toString(){
        return this.getClass().getName()+":"+message;
    }
}

