import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Cart cart = new Cart(scanner);
    Admin admin = new Admin(products, scanner, cart);

    public CommerceSystem() {
        products.add(new Product("전자제품", "Galaxy S24", 1200000, "최신 안드로이드 스마트폰", 15));
        products.add(new Product("전자제품", "iPhone 15", 1350000, "Apple의 최신 스마트폰", 5));
        products.add(new Product("전자제품", "MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10));
        products.add(new Product("전자제품", "AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 30));

        products.add(new Product("의류", "하얀색 셔츠", 40000, "깔끔한 느낌의 셔츠", 200));
        products.add(new Product("의류", "검정 슬랙스", 60000, "보들보들한 재질의 슬랙스", 150));
        products.add(new Product("의류", "연청바지", 65000, "고급 원단을 사용한 청바지", 240));
        products.add(new Product("의류", "회색 후드티", 53000, "착용감이 편안한 후드티", 50));

        products.add(new Product("식품", "계란", 5000, "30판 들어있습니다", 20));
        products.add(new Product("식품", "두부", 3500, "찌개용입니다", 15));
        products.add(new Product("식품", "고추", 2800, "청양고추입니다", 40));
        products.add(new Product("식품", "콩나물", 3000, "싱싱합니다", 35));
    }

    public void start() {
        Category category = new Category(products);

        while (true) {
            System.out.println("[실시간 커머스 플랫폼 메인]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료          | 프로그램 종료");
            System.out.println("6. 관리자 모드");

            if (!cart.isEmpty()) {
                System.out.println("[주문 관리]");
                System.out.println("4. 장바구니 확인 및 결제");
                System.out.println("5. 주문 취소");
            }

            try {
                int select = scanner.nextInt();

                if (select == 0) {
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    scanner.close();
                    break;
                }

                switch (select) {
                    case 1:
                        category.electronics();
                        selectProduct("전자제품");
                        break;
                    case 2:
                        category.clothes();
                        selectProduct("의류");
                        break;
                    case 3:
                        category.food();
                        selectProduct("식품");
                        break;
                    case 4:
                        cart.checkout();
                        break;
                    case 5:
                        cart.cancelOrder();
                        break;
                    case 6:
                        admin.enterAdminMode();
                        break;
                    default:
                        System.out.println("올바른 번호를 입력해주세요.");
                }

            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요.");
                scanner.nextLine();
            }
        }
    }

    private void selectProduct(String categoryName) {
        try {
            int productSelect = scanner.nextInt();

            if (productSelect == 0) {
                return;
            }

            List<Product> filteredProducts = new ArrayList<>();

            for (Product p : products) {
                if (p.getCategory().equals(categoryName)) {
                    filteredProducts.add(p);
                }
            }

            if (productSelect >= 1 && productSelect <= filteredProducts.size()) {
                Product p = filteredProducts.get(productSelect - 1);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | "
                        + p.getExplanation() + " | 재고: " + p.getStock_quantity() + "개");
                cart.addProduct(p);
            } else {
                System.out.println("올바른 상품 번호를 입력해주세요.");
            }

        } catch (Exception e) {
            System.out.println("상품 번호를 입력해주세요.");
            scanner.nextLine();
        }
    }
}