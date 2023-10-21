package hu.progmatic.spring_modul_close.repository;

import hu.progmatic.spring_modul_close.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteById(Integer id);
}
