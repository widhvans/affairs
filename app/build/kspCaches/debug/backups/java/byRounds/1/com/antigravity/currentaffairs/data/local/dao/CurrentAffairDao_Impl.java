package com.antigravity.currentaffairs.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.antigravity.currentaffairs.data.local.entity.CurrentAffairEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CurrentAffairDao_Impl implements CurrentAffairDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CurrentAffairEntity> __insertionAdapterOfCurrentAffairEntity;

  private final EntityDeletionOrUpdateAdapter<CurrentAffairEntity> __updateAdapterOfCurrentAffairEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBookmarkStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOlderThan;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByDate;

  public CurrentAffairDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCurrentAffairEntity = new EntityInsertionAdapter<CurrentAffairEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `current_affairs` (`id`,`titleEnglish`,`titleHindi`,`keyPointsEnglish`,`keyPointsHindi`,`imageUrl`,`category`,`sourceUrl`,`sourceName`,`relevanceScore`,`date`,`isBookmarked`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CurrentAffairEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitleEnglish());
        statement.bindString(3, entity.getTitleHindi());
        statement.bindString(4, entity.getKeyPointsEnglish());
        statement.bindString(5, entity.getKeyPointsHindi());
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImageUrl());
        }
        statement.bindString(7, entity.getCategory());
        statement.bindString(8, entity.getSourceUrl());
        statement.bindString(9, entity.getSourceName());
        statement.bindLong(10, entity.getRelevanceScore());
        statement.bindString(11, entity.getDate());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(12, _tmp);
        statement.bindLong(13, entity.getTimestamp());
      }
    };
    this.__updateAdapterOfCurrentAffairEntity = new EntityDeletionOrUpdateAdapter<CurrentAffairEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `current_affairs` SET `id` = ?,`titleEnglish` = ?,`titleHindi` = ?,`keyPointsEnglish` = ?,`keyPointsHindi` = ?,`imageUrl` = ?,`category` = ?,`sourceUrl` = ?,`sourceName` = ?,`relevanceScore` = ?,`date` = ?,`isBookmarked` = ?,`timestamp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CurrentAffairEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitleEnglish());
        statement.bindString(3, entity.getTitleHindi());
        statement.bindString(4, entity.getKeyPointsEnglish());
        statement.bindString(5, entity.getKeyPointsHindi());
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImageUrl());
        }
        statement.bindString(7, entity.getCategory());
        statement.bindString(8, entity.getSourceUrl());
        statement.bindString(9, entity.getSourceName());
        statement.bindLong(10, entity.getRelevanceScore());
        statement.bindString(11, entity.getDate());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(12, _tmp);
        statement.bindLong(13, entity.getTimestamp());
        statement.bindString(14, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateBookmarkStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE current_affairs SET isBookmarked = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOlderThan = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM current_affairs WHERE date < ? AND isBookmarked = 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByDate = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM current_affairs WHERE date = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<CurrentAffairEntity> affairs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCurrentAffairEntity.insert(affairs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final CurrentAffairEntity affair,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCurrentAffairEntity.insert(affair);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final CurrentAffairEntity affair,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCurrentAffairEntity.handle(affair);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBookmarkStatus(final String id, final boolean isBookmarked,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBookmarkStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isBookmarked ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBookmarkStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOlderThan(final String date, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOlderThan.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, date);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOlderThan.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByDate(final String date, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByDate.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, date);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteByDate.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CurrentAffairEntity>> getAffairsByDate(final String date) {
    final String _sql = "SELECT * FROM current_affairs WHERE date = ? ORDER BY relevanceScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"current_affairs"}, new Callable<List<CurrentAffairEntity>>() {
      @Override
      @NonNull
      public List<CurrentAffairEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEnglish");
          final int _cursorIndexOfTitleHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHindi");
          final int _cursorIndexOfKeyPointsEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsEnglish");
          final int _cursorIndexOfKeyPointsHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsHindi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfSourceName = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceName");
          final int _cursorIndexOfRelevanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "relevanceScore");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<CurrentAffairEntity> _result = new ArrayList<CurrentAffairEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CurrentAffairEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitleEnglish;
            _tmpTitleEnglish = _cursor.getString(_cursorIndexOfTitleEnglish);
            final String _tmpTitleHindi;
            _tmpTitleHindi = _cursor.getString(_cursorIndexOfTitleHindi);
            final String _tmpKeyPointsEnglish;
            _tmpKeyPointsEnglish = _cursor.getString(_cursorIndexOfKeyPointsEnglish);
            final String _tmpKeyPointsHindi;
            _tmpKeyPointsHindi = _cursor.getString(_cursorIndexOfKeyPointsHindi);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpSourceUrl;
            _tmpSourceUrl = _cursor.getString(_cursorIndexOfSourceUrl);
            final String _tmpSourceName;
            _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
            final int _tmpRelevanceScore;
            _tmpRelevanceScore = _cursor.getInt(_cursorIndexOfRelevanceScore);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new CurrentAffairEntity(_tmpId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpIsBookmarked,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<CurrentAffairEntity>> getAffairsByDateAndCategory(final String date,
      final String category) {
    final String _sql = "SELECT * FROM current_affairs WHERE date = ? AND category = ? ORDER BY relevanceScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    _argIndex = 2;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"current_affairs"}, new Callable<List<CurrentAffairEntity>>() {
      @Override
      @NonNull
      public List<CurrentAffairEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEnglish");
          final int _cursorIndexOfTitleHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHindi");
          final int _cursorIndexOfKeyPointsEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsEnglish");
          final int _cursorIndexOfKeyPointsHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsHindi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfSourceName = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceName");
          final int _cursorIndexOfRelevanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "relevanceScore");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<CurrentAffairEntity> _result = new ArrayList<CurrentAffairEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CurrentAffairEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitleEnglish;
            _tmpTitleEnglish = _cursor.getString(_cursorIndexOfTitleEnglish);
            final String _tmpTitleHindi;
            _tmpTitleHindi = _cursor.getString(_cursorIndexOfTitleHindi);
            final String _tmpKeyPointsEnglish;
            _tmpKeyPointsEnglish = _cursor.getString(_cursorIndexOfKeyPointsEnglish);
            final String _tmpKeyPointsHindi;
            _tmpKeyPointsHindi = _cursor.getString(_cursorIndexOfKeyPointsHindi);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpSourceUrl;
            _tmpSourceUrl = _cursor.getString(_cursorIndexOfSourceUrl);
            final String _tmpSourceName;
            _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
            final int _tmpRelevanceScore;
            _tmpRelevanceScore = _cursor.getInt(_cursorIndexOfRelevanceScore);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new CurrentAffairEntity(_tmpId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpIsBookmarked,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAffairById(final String id,
      final Continuation<? super CurrentAffairEntity> $completion) {
    final String _sql = "SELECT * FROM current_affairs WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CurrentAffairEntity>() {
      @Override
      @Nullable
      public CurrentAffairEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEnglish");
          final int _cursorIndexOfTitleHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHindi");
          final int _cursorIndexOfKeyPointsEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsEnglish");
          final int _cursorIndexOfKeyPointsHindi = CursorUtil.getColumnIndexOrThrow(_cursor, "keyPointsHindi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfSourceName = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceName");
          final int _cursorIndexOfRelevanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "relevanceScore");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final CurrentAffairEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitleEnglish;
            _tmpTitleEnglish = _cursor.getString(_cursorIndexOfTitleEnglish);
            final String _tmpTitleHindi;
            _tmpTitleHindi = _cursor.getString(_cursorIndexOfTitleHindi);
            final String _tmpKeyPointsEnglish;
            _tmpKeyPointsEnglish = _cursor.getString(_cursorIndexOfKeyPointsEnglish);
            final String _tmpKeyPointsHindi;
            _tmpKeyPointsHindi = _cursor.getString(_cursorIndexOfKeyPointsHindi);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpSourceUrl;
            _tmpSourceUrl = _cursor.getString(_cursorIndexOfSourceUrl);
            final String _tmpSourceName;
            _tmpSourceName = _cursor.getString(_cursorIndexOfSourceName);
            final int _tmpRelevanceScore;
            _tmpRelevanceScore = _cursor.getInt(_cursorIndexOfRelevanceScore);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new CurrentAffairEntity(_tmpId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpIsBookmarked,_tmpTimestamp);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAffairsCountByDate(final String date,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM current_affairs WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllTitles(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT titleEnglish FROM current_affairs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllDates(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT date FROM current_affairs ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
