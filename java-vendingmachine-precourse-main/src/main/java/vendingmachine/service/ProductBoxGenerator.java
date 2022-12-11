package vendingmachine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import vendingmachine.model.ProductInfo;

public class ProductBoxGenerator {

    public Map<String, ProductInfo> generate(List<String> infos) {
        Map<String, ProductInfo> result = new HashMap<>();
        for (String info : infos) {
            validate(info);
            String[] productInfos = generateInfo(info);
            validateProductName(productInfos[0]);
            validateDuplicate(result, productInfos);
            storeProduct(result, productInfos);
        }
        return result;
    }

    private void validate(String info) {
        if (!Pattern.matches("\\[.*,[0-9]+,[0-9]+\\]", info)) {
            throw new IllegalArgumentException("올바르지 않은 상품 정보를 입력하였습니다.");
        }
    }

    private void validateProductName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("상품 이름은 존재해야 합니다.");
        }
    }

    private void validateDuplicate(Map<String, ProductInfo> result, String[] productInfos) {
        if (result.containsKey(productInfos[0])) {
            throw new IllegalArgumentException("중복되는 상품을 입력하였습니다.");
        }
    }

    private void storeProduct(Map<String, ProductInfo> result, String[] productInfos) {
        ProductInfo productInfo = new ProductInfo(Integer.parseInt(productInfos[1]),
                Integer.parseInt(productInfos[2]));
        result.put(productInfos[0], productInfo);
    }

    private String[] generateInfo(String info) {
        return info.substring(1, info.length()-1).split(",");
    }
}
