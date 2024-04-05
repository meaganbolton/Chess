package result;
/**
 * The FailureResult class represents the result of an operation that has failed, providing the failure message.
 */
public class BaseResult {
    /**
     * Creating a string to be the message of the base result.
     */
    private String message = null;
    /**
     * Constructs a new BaseResult.
     */
    public BaseResult(){
    }

    public void setMessage(String message){this.message = message;}
    public String getMessage() {
        return message;
    }
}