import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Admin {
    private final List<Product> products;
    private final Scanner scanner;
    private final Cart cartManager;
    private final String adminPassword = "admin123";

    public Admin(List<Product> products, Scanner scanner, Cart cartManager) {
        this.products = products;
        this.scanner = scanner;
        this.cartManager = cartManager;
    }

    public void enterAdminMode() {
        int failCount = 0;

        while (failCount < 3) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String input = scanner.next();

            if (adminPassword.equals(input)) {
                System.out.println("관리자 인증에 성공했습니다.");
                adminMenu();
                return;
            } else {
                failCount++;
                System.out.println("비밀번호가 틀렸습니다. +" + failCount + "번 남았습니다.");
            }
        }

        System.out.println("비밀번호 입력 3회 실패하였습니다. 메인 메뉴로 돌아갑니다.");
    }

    private void adminMenu() {
        while (true) {
            System.out.println("[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");

            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    printAllProducts();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }

    private void addProduct() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");

        int categorySelect = scanner.nextInt();
        scanner.nextLine();

        String categoryName = getCategoryName(categorySelect);
        if (categoryName == null) {
            System.out.println("올바른 카테고리를 선택해주세요.");
            return;
        }

        System.out.println("[ " + categoryName + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        if (findProductInCategory(categoryName, name) != null) {
            System.out.println("같은 카테고리에 이미 같은 이름의 상품이 존재합니다.");
            return;
        }

        System.out.print("가격을 입력해주세요: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.print("상품 설명을 입력해주세요: ");
        String explanation = scanner.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int stock = scanner.nextInt();

        Product newProduct = new Product(categoryName, name, price, explanation, stock);

        System.out.println(formatProduct(newProduct));
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int confirm = scanner.nextInt();

        if (confirm == 1) {
            products.add(newProduct);
            System.out.println("상품이 성공적으로 추가되었습니다!");
        } else {
            System.out.println("상품 추가가 취소되었습니다.");
        }
    }

    private void updateProduct() {
        scanner.nextLine();
        System.out.print("수정할 상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        Product product = findProductByName(name);

        if (product == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }

        System.out.println("현재 상품 정보: " + formatProduct(product));
        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");

        int select = scanner.nextInt();
        scanner.nextLine();

        switch (select) {
            case 1:
                int oldPrice = product.getPrice();
                System.out.println("현재 가격: " + formatNumber(oldPrice) + "원");
                System.out.print("새로운 가격을 입력해주세요: ");
                int newPrice = scanner.nextInt();
                product.setPrice(newPrice);
                System.out.println(product.getName() + "의 가격이 "
                        + formatNumber(oldPrice) + "원 → " + formatNumber(newPrice) + "원으로 수정되었습니다.");
                break;

            case 2:
                String oldExplanation = product.getExplanation();
                System.out.println("현재 설명: " + oldExplanation);
                System.out.print("새로운 설명을 입력해주세요: ");
                String newExplanation = scanner.nextLine();
                product.setExplanation(newExplanation);
                System.out.println(product.getName() + "의 설명이 수정되었습니다.");
                break;

            case 3:
                int oldStock = product.getStock_quantity();
                System.out.println("현재 재고수량: " + oldStock + "개");
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int newStock = scanner.nextInt();
                product.setStock_quantity(newStock);
                System.out.println(product.getName() + "의 재고수량이 "
                        + oldStock + "개 → " + newStock + "개로 수정되었습니다.");
                break;

            default:
                System.out.println("올바른 번호를 입력해주세요.");
        }
    }

    private void deleteProduct() {
        scanner.nextLine();
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        Product product = findProductByName(name);

        if (product == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }

        System.out.println("삭제할 상품 정보: " + formatProduct(product));
        System.out.println("정말 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int confirm = scanner.nextInt();

        if (confirm == 1) {
            products.remove(product);
            cartManager.removeDeletedProduct(product);
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("상품 삭제가 취소되었습니다.");
        }
    }

    private void printAllProducts() {
        System.out.println("[ 전체 상품 현황 ]");

        System.out.println("▶ 전자제품");
        printProductsByCategory("전자제품");

        System.out.println("▶ 의류");
        printProductsByCategory("의류");

        System.out.println("▶ 식품");
        printProductsByCategory("식품");
    }

    private void printProductsByCategory(String categoryName) {
        boolean found = false;

        for (Product product : products) {
            if (product.getCategory().equals(categoryName)) {
                System.out.println("- " + formatProduct(product));
                found = true;
            }
        }

        if (!found) {
            System.out.println("- 등록된 상품이 없습니다.");
        }
    }

    private Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private Product findProductInCategory(String category, String name) {
        for (Product product : products) {
            if (product.getCategory().equals(category) && product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private String getCategoryName(int select) {
        switch (select) {
            case 1:
                return "전자제품";
            case 2:
                return "의류";
            case 3:
                return "식품";
            default:
                return null;
        }
    }

    private String formatProduct(Product product) {
        return product.getName() + " | " + formatNumber(product.getPrice()) + "원 | "
                + product.getExplanation() + " | 재고: " + product.getStock_quantity() + "개";
    }

    private String formatNumber(int number) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(number);
    }
}