package stream.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long productId;//商品ID
    private String productName;//商品名称
    private Double productPrice;//商品价格
    private Integer productQuantity;//商品数量
}
