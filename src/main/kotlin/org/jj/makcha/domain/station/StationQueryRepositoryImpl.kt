package org.jj.makcha.domain.station

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor

class StationQueryRepositoryImpl(
    private val executor: KotlinJdslJpqlExecutor
) : StationQueryRepository {

    override fun findByConditions(line: String?, query: String?): List<Station> {
        return executor.findAll {
            select(entity(Station::class))
                .from(entity(Station::class))
                .where(
                    and(
                        line?.let { path(Station::line).eq(line) },
                        query?.let { (path(Station::name).like("%$query%")) }
                    )
                )
        }.filterNotNull()
    }

    override fun findAllNames(): Set<String> {
        return executor.findAll {
            select(path(Station::name))
                .from(entity(Station::class))
        }.filterNotNull().toSet()
    }
}
