package inf.anibele.jpa_query_methods.repository;

import inf.anibele.jpa_query_methods.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //Derivação de Nome de Metodo

    //1 - Consultas por propriedades
    // Encontra os usuários pelo nome
    List<User> findByName(String name);

    // Encontra os usuários pelo email
    List<User> findByEmail(String email);

    // Encontra um usuários ativos (true)
    List<User> findByActive(boolean active);

    //2 - Consultas parciais
    // Buscar usuários cujo nome contenha uma string
    List<User> findByNameContaining(String name);

    //Buscar usuários cujo nome comece com um prefixo
    List<User> findByNameStartingWith(String name);

    //Buscar usuários cujo nome termine com um sufixo
    List<User> findByNameEndingWith(String name);

    //3 - Consultas combinadas
    //Buscar usuários por nome e email
    List<User> findByNameAndEmail(String name, String email);

    //Buscar usuários por nome ou email
    List<User> findByNameOrEmail(String name, String email);

    //Buscar usuários ativos com idade maior que um valor
    List<User> findByActiveAndAgeGreaterThan(boolean active, int age);

    //4 - Consultas com intervalos
    //Buscar usuários com idade entre dois valores
    List<User> findByAgeBetween(int a1, int a2);

    //Buscar usuários com idade maior ou igual a X
    List<User> findByAgeGreaterThanEqual(int age);

    //Buscar usuários com idade menor que X
    List<User> findByAgeLessThan(int age);

    //5 - Consultas com ordenação
    //Buscar usuários por email ordenando por nome ascendente
    List<User> findByEmailOrderByNameAsc(String email);

    //Buscar usuários ativos ordenando por idade decrescente
    List<User> findByActiveOrderByAgeDesc(boolean active);

    //6 - Negação e listas
    //Buscar usuários cujo email seja diferente de um valor
    List<User> findByEmailNot(String email);

    //Buscar usuários com idade dentro de uma lista de idades (IN)
    List<User> findByAgeIn(List<Integer> age);

    //Buscar usuários com idade fora de uma lista (NOT IN)
    List<User> findByAgeNotIn(List<Integer> age);

    //7 - Case insensitive
    //Buscar usuários por nome ignorando maiúsculas/minúsculas
    List<User> findByNameIgnoreCase(String name);

    //8 - Contagem e existência
    //Criar um metodo que conte usuários ativos
    Integer countByActive(boolean active);

    //Criar um metodo que verifique se existe usuário com determinado email
    boolean existsByEmail(String email);

    /*9 - Exercício de análise
    Dado o metodo findByNameContainingAndAgeGreaterThanAndActiveTrue,
    explique qual consulta será gerada.
    Explicação: O metodo findByNameContainingAndAgeGreaterThanAndActiveTrue irá gerar
    uma consulta que busca por usuários cujo nome contenha uma determinada string (name),
    cuja idade seja maior que um valor específico (age) e que estejam ativos (active = true).
    */

    //10 - Exercício de refatoração
    //Dado um metodo muito longo com vários critérios,
    //proponha uma alternativa usando @Query
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% AND u.age > :age AND u.active = true")
    List<User> findActiveUsersByNameAndAge(String name, int age);

    // Consulta por Anotação (@Query)
    //1 - Consulta JPQL
    // Retornar todos os usuários
    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

    // Retornar usuários por nome (parâmetro nomeado)
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> findUsersByName(@Param("name") String name);

    //2 - Consultas com filtros
    // Buscar usuários com idade maior que um valor
    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersWithAgeGreaterThan(@Param("age") Integer age);

    // Buscar usuários ativos com determinado domínio de email(ex: “@gmail.com”)
    @Query("SELECT u FROM User u WHERE u.active = true AND u.email LIKE %:dominio")
    List<User> findUsersWithEmailContaining(@Param("dominio") String dominio);

    //3 - Trabalhando co  LIKE
    // Buscar usuários cujo nome termine com determinado texto
    @Query("SELECT u FROM User u WHERE u.name LIKE %:text")
    List<User> findUsersWithNameEndingWith(@Param("text") String text);

    // Buscar usuários cujo nome contenha determinado texto
    @Query("SELECT u FROM User u WHERE u.name LIKE %:text%")
    List<User> findUsersWithNameContaining(@Param("text") String text);

    //4 - Consultas com múltiplos critérios
    // Buscar usuários ativos com idade entre dois valores e nome contendo uma string
    @Query("SELECT u FROM User u WHERE u.active = true AND u.age BETWEEN :minAge AND :maxAge AND u.name LIKE %:text%")
    List<User> findActiveUsersByAgeRangeAndName(
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("text") String text
    );

    //5 - Crie uma consulta que retorne apenas nome e email (Object[] ou DTO)
    @Query("SELECT u.name, u.email FROM User u")
    List<User> findNameAndEmail();

}