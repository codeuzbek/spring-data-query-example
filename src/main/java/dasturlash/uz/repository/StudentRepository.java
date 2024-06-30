package dasturlash.uz.repository;

import dasturlash.uz.entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    @Query("from StudentEntity")
    List<StudentEntity> getAll();

    @Query("select s from StudentEntity s")
    List<StudentEntity> all();

    @Query("from StudentEntity s where s.age > 18")
    List<StudentEntity> getAllOlderThan18();

    @Query("from StudentEntity s where s.id =:idParam ")
    StudentEntity getById(@Param("idParam") Integer idParam);

    @Query("from StudentEntity s where s.name =:nameParam and s.surname =:surnameParam")
    List<StudentEntity> getAllByNameAndSurname(@Param("nameParam") String nameParam,
                                               @Param("surnameParam") String surnameParam);

    @Query("from StudentEntity s where s.name =?1 and s.surname=?2")
    List<StudentEntity> getAllByNameAndSurnameUsingPP(String name, String surname);

//    @Query("from StudentEntity s where s.name =?1 limit 2")
//    List<StudentEntity> getByNameLimitTwo(String name);

    @Query("from StudentEntity s where s.name =?1")
    List<StudentEntity> getByNameLimitOne(String name, Pageable pageable);

    @Query(value = "select * from student s where s.name =:nameParam and s.surname =:surnameParam", nativeQuery = true)
    List<StudentEntity> getAllByNameAndSurnameNative(@Param("nameParam") String nameParam,
                                                     @Param("surnameParam") String surnameParam);

    @Modifying
    @Transactional
    @Query("update StudentEntity set name = ?2 where id = ?1")
    int updateName(Integer id, String name);

    @Query("from StudentEntity s where s.name =?1 order by s.age desc ")
    List<StudentEntity> getByNameOrderByAgeDesc(String name);

    @Query("from StudentEntity s where s.name =?1")
    List<StudentEntity> getByNameSort(String name, Sort sort);
}
