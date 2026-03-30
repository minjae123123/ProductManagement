import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    public void start() {
        Category category = new Category(products);

        while (true) {
            System.out.println("[실시간 커머스 플랫폼 메인]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료          |프로그램 종료");
            try {
                int select = scanner.nextInt();
                if (select == 0) {
                    System.out.println("커머스 플랫픔을 종료합니다.");
                    scanner.close();
                    break;
                }

                switch (select) {
                    case 1:
                        //전자제품 카테고리
                        category.electronics();
                        if (select_product()) continue;
                        break;
                    case 2:
                        //의류 카테고리
                        category.clothes();
                        if (select_product()) continue;
                        break;
                    case 3:
                        //식품 카테고리
                        category.food();
                        if (select_product()) continue;
                        break;
                }

            } catch (Exception e) {
                System.out.println("1~3 중 입력하세요. (0은 종료)");
                scanner.nextLine();
            }
        }
    }

    private boolean select_product() {
        try {
            int product_select = scanner.nextInt();
            if (product_select == 0) {
                return true;
            } else if (product_select == 1) {
                Product p = products.get(0);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
            } else if (product_select == 2) {
                Product p = products.get(1);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
            } else if (product_select == 3) {
                Product p = products.get(2);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
            } else if (product_select == 4) {
                Product p = products.get(3);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
            }
        } catch (Exception e) {
            System.out.println("상품 번호를 입력해주세요");
            scanner.nextLine();
        }
        return false;
    }
}
