package com.excercise.items.dao;import com.excercise.items.model.Item;import java.util.List;import org.springframework.context.annotation.Configuration;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Modifying;import org.springframework.data.jpa.repository.Query;import org.springframework.transaction.annotation.EnableTransactionManagement;import org.springframework.transaction.annotation.Transactional;@Configuration@EnableTransactionManagementpublic interface ItemDao extends JpaRepository<Item, Long> {  @Modifying  @Transactional  @Query("delete from Item i where i.modifiedTs < :timestamp")  void deleteBeforeTimestamp(long timestamp);  @Query("select i from Item i join i.tags t where t in :tags and i.value = :value order by "      + "case when :orderBy = 'value' and :orderDirection = 'asc' then i.value end asc, "      + "case when :orderBy = 'value' and :orderDirection = 'desc' then i.value end desc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'asc' then i.modifiedTs end asc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'desc' then i.modifiedTs end desc "  )  Page<Item> findByParameters(List<String> tags, Long value, String orderBy, String orderDirection, Pageable pageable);  @Query("select i from Item i where i.value = :value order by "      + "case when :orderBy = 'value' and :orderDirection = 'asc' then i.value end asc, "      + "case when :orderBy = 'value' and :orderDirection = 'desc' then i.value end desc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'asc' then i.modifiedTs end asc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'desc' then i.modifiedTs end desc "  )  Page<Item> findByParameters(Long value, String orderBy, String orderDirection, Pageable pageable);  @Query("select i from Item i join i.tags t where t in :tags order by "      + "case when :orderBy = 'value' and :orderDirection = 'asc' then i.value end asc, "      + "case when :orderBy = 'value' and :orderDirection = 'desc' then i.value end desc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'asc' then i.modifiedTs end asc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'desc' then i.modifiedTs end desc "  )  Page<Item> findByParameters(List<String> tags, String orderBy, String orderDirection, Pageable pageable);  @Query("select i from Item i order by "      + "case when :orderBy = 'value' and :orderDirection = 'asc' then i.value end asc, "      + "case when :orderBy = 'value' and :orderDirection = 'desc' then i.value end desc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'asc' then i.modifiedTs end asc, "      + "case when :orderBy = 'modifiedTs' and :orderDirection = 'desc' then i.modifiedTs end desc "  )  Page<Item> findByParameters(String orderBy, String orderDirection, Pageable pageable);}