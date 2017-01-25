package web.store.order.web.exception;
/**
 * 
* @ClassName: OrderException 
* @Description: 自定义异常
* @author MangoDai 96555734@qq.com
* @date 2017年1月19日 下午8:12:59 
*
 */
public class OrderException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public OrderException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OrderException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
