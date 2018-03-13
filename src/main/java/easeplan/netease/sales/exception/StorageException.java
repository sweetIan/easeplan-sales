package easeplan.netease.sales.exception;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
public class StorageException extends RuntimeException {
    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
