package xyz.wongs.drunkard.war3.web.zonecode.exception;

import java.io.PrintWriter;

/**
 * @ClassName NullException
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:03
 * @Version 1.0.0
*/
public class NullException extends Exception {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public NullException(){

	}

	public NullException(String msg){
		super(msg);
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
	}



}
