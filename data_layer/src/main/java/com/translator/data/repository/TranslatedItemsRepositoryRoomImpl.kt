import com.translator.data.local.HistoryDao
import com.translator.data.local.HistoryEntity
import com.translator.domain.models.HistoryItem
import com.translator.domain.models.Item
import com.translator.domain.repository.TranslatedItemsRepository
import javax.inject.Inject

class TranslatedItemsRepositoryRoomImpl @Inject constructor(
    private val dao: HistoryDao
) : TranslatedItemsRepository {

    override suspend fun addItem(item: Item): List<Item> {
        dao.addHistoryItem(
            HistoryEntity(
                contents = item.contents
            )
        )
        return getItems()
    }

    override suspend fun getItems(): List<Item> {
        return dao.getHistory().map { HistoryItem(it.id, it.contents) }
    }

    override suspend fun removeFromItems(item: Item): List<Item> {
        dao.removeFromHistory(HistoryEntity(item.id, item.contents))
        return getItems()
    }

    override suspend fun clearItems(): List<Item> {
        dao.clearHistory()
        return getItems()
    }
}
