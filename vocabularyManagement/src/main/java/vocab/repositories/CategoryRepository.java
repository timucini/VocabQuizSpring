package vocab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vocab.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
}
