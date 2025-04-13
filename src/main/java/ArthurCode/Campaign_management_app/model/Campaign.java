package ArthurCode.Campaign_management_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private String campaignName;

    @ElementCollection
    private List<String> keywords;

    @Column(nullable = false)
    private BigDecimal bidAmount;

    @Column(nullable = false)
    private BigDecimal campaignFund;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private Integer radius;

    @ManyToOne
    private AppUser owner;

}
