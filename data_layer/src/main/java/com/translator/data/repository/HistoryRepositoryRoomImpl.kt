import com.translator.data.local.HistoryDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.HistoryItem
import com.translator.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryRoomImpl @Inject constructor(
    private val dao: HistoryDao
) : HistoryRepository {

    override suspend fun addHistoryItem(historyItem: HistoryItem): List<HistoryItem> {
        dao.addHistoryItem(
            HistoryEntity(
                description = historyItem.contents
            )
        )
        return getHistory()
    }

    override suspend fun getHistory(): List<HistoryItem> {
        return dao.getHistory().map { HistoryItem(it.id, it.description) }
    }

    override suspend fun removeFromHistory(item: HistoryItem): List<HistoryItem> {
        dao.removeFromHistory(HistoryEntity(item.id, item.contents))
        return getHistory()
    }

    override suspend fun clearHistory(): List<HistoryItem> {
        dao.clearHistory()
        return getHistory()
    }
}
