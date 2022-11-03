package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="RecoveryRooms")
public class RecoveryRoom extends BaseEntity {
	
	@Column(nullable=false)
	@Size(min=3, max=50)
    String name;
	
	@Column(nullable=false)
    @Min(0)
    double size;
	
	@Column(nullable=false)
    boolean secure;
    
    @ManyToOne
    @JoinColumn(name = "roomType")
    RecoveryRoomType roomType;
}
