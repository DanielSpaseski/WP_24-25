package mk.finki.ukim.mk.lab_b.repository.jpa;

import mk.finki.ukim.mk.lab_b.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTrackId(String trackId);
    List<Song> findAllByAlbum_Id(Long albumId);
}
