package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoveryRoomService {
	
	@Autowired
	private RecoveryRoomRepository rrr;
	
	public RecoveryRoomService(RecoveryRoomRepository rrr) {
		this.rrr = rrr;
	}
	
    public List<RecoveryRoom> getAll(){
        return rrr.findAll();
    }

    public List<RecoveryRoomType> getAllRecoveryRoomTypes(){
        return rrr.findAllRecoveryRoomTypes();
    }

    public RecoveryRoomType getRecoveryRoomType(String typeName) {
        return rrr.getRecoveryRoomType(typeName);
    }

    // Esta es la solución, pero Eclipse me dice que rollbackFor no está definido
    // @Transactional(rollbackFor = DuplicatedRoomNameException.class)
    @Transactional
    public RecoveryRoom save(RecoveryRoom p) throws DuplicatedRoomNameException {
    	for (RecoveryRoom rr :getAll()) {
			if(rr.getName().equals(p.getName()) && rr.getRoomType().getName().equals(p.getRoomType().getName()) && rr.getId() != p.getId()) {
				throw new DuplicatedRoomNameException();
			}
		}
        return rrr.save(p);       
    }

    
}
