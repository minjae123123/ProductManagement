import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<Product> carts = new ArrayList<>();
    private Scanner scanner;

    public Cart(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean isEmpty() {
        return carts.isEmpty();
    }

    public void addProduct(Product p) {
        if (p.getStock_quantity() <= 0) {
            System.out.println("재고가 없는 상품입니다.");
            return;
        }

        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int addCart = scanner.nextInt();

        if (addCart == 1) {
            if (carts.contains(p)) {
                p.setCartCount(p.getCartCount() + 1);
            } else {
                p.setCartCount(1);
                carts.add(p);
            }

            System.out.println(p.getName() + "가 장바구니에 추가되었습니다. (수량: " + p.getCartCount() + ")");
        }
    }

    public void showCart() {
        if (carts.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("[장바구니 내역]");
        for (Product cart : carts) {
            System.out.println(cart.getName() + " | " + cart.getPrice() + " | " + cart.getExplanation() + " | 수량: " + cart.getCartCount());
        }

        System.out.println("[총 주문 금액]");
        System.out.println(getTotalPrice() + "원");
    }

    public int getTotalPrice() {
        int sum = 0;

        for (Product cart : carts) {
            sum += cart.getPrice() * cart.getCartCount();
        }

        return sum;
    }

    public void checkout() {
        if (carts.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        showCart();
        System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
        int confirmOrder = scanner.nextInt();

        if (confirmOrder == 1) {
            System.out.println("주문이 완료되었습니다! 총 금액: " + getTotalPrice() + "원");

            for (Product cart : carts) {
                int currentStock = cart.getStock_quantity();
                int count = cart.getCartCount();

                if (currentStock >= count) {
                    cart.setStock_quantity(currentStock - count);
                    System.out.println(cart.getName() + "의 재고가 "
                            + currentStock + "개 -> " + cart.getStock_quantity() + "개로 업데이트되었습니다.");
                } else {
                    System.out.println(cart.getName() + "은(는) 재고가 부족합니다.");
                }

                cart.setCartCount(0);
            }

            carts.clear();
        }
    }

    public void cancelOrder() {
        if (carts.isEmpty()) {
            System.out.println("취소할 주문이 없습니다.");
            return;
        }

        for (Product cart : carts) {
            cart.setCartCount(0);
        }

        carts.clear();
        System.out.println("진행중인 주문이 취소되었습니다.");
    }

    public void removeDeletedProduct(Product product) {
        if (carts.contains(product)) {
            product.setCartCount(0);
            carts.remove(product);
            System.out.println("삭제된 상품이 장바구니에서 제거되었습니다.");
        }
    }
}