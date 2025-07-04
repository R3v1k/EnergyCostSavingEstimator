package revik.com.energycostsavingestimator.user.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByUserId(Long userId);
    long countByUserIdAndType(Long userId, RoomType type);
}
