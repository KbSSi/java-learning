package exception;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/13
 */
public class CustomExceptionDemo {
    public static void main(String[] args) {
        try {
            String token = login("admin", "11");
            System.out.println(token);
        }catch (UserNotFoundException | PasswordErrorException e){

            e.printStackTrace();

        }
    }

    static String login(String username, String password){
        if ("admin".equals(username)){
            if ("11111".equals(password)) {
                return username + "login successes.";
            }else{
                throw new PasswordErrorException("PassWord Error!");
            }
        }else{
            throw new UserNotFoundException("User Not Found!");
        }
    }
}
