package net.mbonnin.testsqldelight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.android.AndroidSqliteDriver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Database(driver = AndroidSqliteDriver(Database.Schema, this, "test"),
            hockeyPlayerDb2Adapter = HockeyPlayerDb2.Adapter(
                positionAdapter = EnumColumnAdapter()
            ),
            hockeyPlayerDbAdapter = HockeyPlayerDb.Adapter(
                positionAdapter = EnumColumnAdapter()
            )
        )

        db.hockeyQueries.selectAll().addListener(object: Query.Listener {
            override fun queryResultsChanged() {
                Log.d("DB", "query changed")
            }
        })
    }
}
