import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products = new ArrayList<>();
    List<Product> carts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    public void start() {
        Category category = new Category(products);
        int sum = 0;

        while (true) {
            System.out.println("[실시간 커머스 플랫폼 메인]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료          |프로그램 종료");

            if(!carts.isEmpty()) {
                System.out.println("[주문 관리]");
                System.out.println("4. 장바구니 확인    |장바구니 확인 후 주문합니다.");
                System.out.println("5. 주문 취소    |진행중인 주문을 취소합니다.");
            }

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
                        products_clear(category);
                        break;
                    case 2:
                        //의류 카테고리
                        category.clothes();
                        if (select_product()) continue;
                        products_clear(category);
                        break;
                    case 3:
                        //식품 카테고리
                        category.food();
                        if (select_product()) continue;
                        products_clear(category);
                        break;
                    case 4:
                        System.out.println("아래와 같이 주문하시겠습니까?");

                        System.out.println("[장바구니 내역]");
                        for (Product cart : carts) {
                            System.out.println(cart.getName() + "|" + cart.getPrice()+ "|" + cart.getExplanation()+ "|");
                        }

                        System.out.println("[총 주문 금액]");
                        for (Product cart : carts) {
                            int price = cart.getPrice();
                            sum += price;
                        }
                        System.out.println(sum + "원");

                        System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
                        int confirm_order = scanner.nextInt();

                        if(confirm_order == 1) {
                            System.out.println("주문이 완료되었습니다! 총 금액: " + sum + "원");
                            for (Product cart : carts) {
                                System.out.println(cart.getName() + "의 재고가 " + cart.getStock_quantity() + "개 -> " + (cart.getStock_quantity() - 1) + "개로 업데이트되었습니다.");
                            }
                            sum = 0;
                            carts.clear();
                        }
                }
            } catch (Exception e) {
                System.out.println("1~3 중 입력하세요. (0은 종료)");
                scanner.nextLine();
            }
        }
    }

    private static void products_clear(Category category) {
        List<Product> list = category.getProducts();
        list.clear();
    }

    private boolean select_product() {
        try {
            int product_select = scanner.nextInt();
            if (product_select == 0) {
                return true;
            } else if (product_select == 1) {
                Product p = products.get(0);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
                add_carts(p);
            } else if (product_select == 2) {
                Product p = products.get(1);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
                add_carts(p);
            } else if (product_select == 3) {
                Product p = products.get(2);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
                add_carts(p);
            } else if (product_select == 4) {
                Product p = products.get(3);
                System.out.println("★ 선택한 상품: " + p.getName() + " | " + p.getPrice() + "원 | " + p.getExplanation() + "| 재고: " + p.getStock_quantity() + "개");
                add_carts(p);
            }
        } catch (Exception e) {
            System.out.println("상품 번호를 입력해주세요");
            scanner.nextLine();
        }
        return false;
    }

    private void add_carts(Product p) {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1.확인        2.취소");
        int addCart = scanner.nextInt();
        if(addCart == 1) {
            System.out.println(p.getName() + "가 장바구니에 추가되었습니다.");
            carts.add(p);
        }
    }
}
