import java.util.List;

public class Category {

    // Product 클래스를 List로 관리
    List<Product> products;

    public Category(List<Product> products) {
        this.products = products;
    }

    // 구조에 맞게 메소드 선언
    public void electronics() {
        System.out.println("[전자제품 카테고리]");
        if(products.isEmpty()) {
            products.add(new Product("Galaxy S24", 1200000, "최신 안드로이드 스마트폰", 15));
            products.add(new Product("iPhone 15 ", 1350000, "Apple의 최신 스마트폰", 5));
            products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10));
            products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 30));
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + "." + p.getName() + "     |" + p.getPrice() +"|" + p.getExplanation());
        }
        System.out.println("0. 뒤로가기");
    }

    public void clothes() {
        System.out.println("[의류 카테고리]");
        if(products.isEmpty()) {
            products.add(new Product("하얀색 셔츠", 40000, "깔끔한 느낌의 셔츠", 200));
            products.add(new Product("검정 슬랙스 ", 60000, "보들보들한 재질의 슬랙스", 150));
            products.add(new Product("연청바지", 65000, "고급 원단을 사용한 청바지", 240));
            products.add(new Product("회색 후드티", 53000, "착용감이 편안한 후드티", 50));
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + "." + p.getName() + "     |" + p.getPrice() +"|" + p.getExplanation());
        }
        System.out.println("0. 뒤로가기");
    }

    public void food() {
        System.out.println("[식품 카테고리]");
        if(products.isEmpty()) {
            products.add(new Product("계란", 5000, "30판 들어있습니다", 20));
            products.add(new Product("두부", 3500, "찌개용입니다", 15));
            products.add(new Product("고추", 2800, "청양고추입니다", 40));
            products.add(new Product("콩나물", 3000, "싱싱합니다", 35));
        }
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + "." + p.getName() + "     |" + p.getPrice() +"|" + p.getExplanation());
        }
        System.out.println("0. 뒤로가기");

    }
}
