import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products;
    Scanner scanner = new Scanner(System.in);

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    public void start() {
        while (true) {
            System.out.println("[실시간 커머스 플랫폼 - 전자제품]");

            for (int i = 0; i < products.size() ; i++) {
                Product p = products.get(i);
                System.out.println((i + 1)+"." + p.getName() + "     |" + p.getPrice() + "|" + p.getExplanation());
            }

            System.out.println("0. 종료          |프로그램 종료");

            try {
                int exit = scanner.nextInt();
                if (exit == 0) {
                    System.out.println("커머스 플랫픔을 종료합니다.");
                    scanner.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println("1~4 중 입력하세요. (0은 종료)");
                scanner.nextLine();
            }
        }
    }
}
