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
import com.antigravity.currentaffairs.data.local.entity.BookmarkEntity;
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
public final class BookmarkDao_Impl implements BookmarkDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookmarkEntity> __insertionAdapterOfBookmarkEntity;

  private final EntityDeletionOrUpdateAdapter<BookmarkEntity> __deletionAdapterOfBookmarkEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public BookmarkDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookmarkEntity = new EntityInsertionAdapter<BookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bookmarks` (`affairId`,`titleEnglish`,`titleHindi`,`keyPointsEnglish`,`keyPointsHindi`,`imageUrl`,`category`,`sourceUrl`,`sourceName`,`relevanceScore`,`date`,`timestamp`,`bookmarkedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookmarkEntity entity) {
        statement.bindString(1, entity.getAffairId());
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
        statement.bindLong(12, entity.getTimestamp());
        statement.bindLong(13, entity.getBookmarkedAt());
      }
    };
    this.__deletionAdapterOfBookmarkEntity = new EntityDeletionOrUpdateAdapter<BookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bookmarks` WHERE `affairId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookmarkEntity entity) {
        statement.bindString(1, entity.getAffairId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM bookmarks WHERE affairId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final BookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookmarkEntity.insert(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final BookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBookmarkEntity.handle(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String affairId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, affairId);
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookmarkEntity>> getAllBookmarks() {
    final String _sql = "SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAffairId = CursorUtil.getColumnIndexOrThrow(_cursor, "affairId");
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
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfBookmarkedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkedAt");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final String _tmpAffairId;
            _tmpAffairId = _cursor.getString(_cursorIndexOfAffairId);
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
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpBookmarkedAt;
            _tmpBookmarkedAt = _cursor.getLong(_cursorIndexOfBookmarkedAt);
            _item = new BookmarkEntity(_tmpAffairId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpTimestamp,_tmpBookmarkedAt);
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
  public Flow<List<BookmarkEntity>> getBookmarksByDate(final String date) {
    final String _sql = "SELECT * FROM bookmarks WHERE date = ? ORDER BY bookmarkedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAffairId = CursorUtil.getColumnIndexOrThrow(_cursor, "affairId");
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
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfBookmarkedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkedAt");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final String _tmpAffairId;
            _tmpAffairId = _cursor.getString(_cursorIndexOfAffairId);
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
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpBookmarkedAt;
            _tmpBookmarkedAt = _cursor.getLong(_cursorIndexOfBookmarkedAt);
            _item = new BookmarkEntity(_tmpAffairId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpTimestamp,_tmpBookmarkedAt);
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
  public Object getBookmarkById(final String affairId,
      final Continuation<? super BookmarkEntity> $completion) {
    final String _sql = "SELECT * FROM bookmarks WHERE affairId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, affairId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookmarkEntity>() {
      @Override
      @Nullable
      public BookmarkEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAffairId = CursorUtil.getColumnIndexOrThrow(_cursor, "affairId");
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
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfBookmarkedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkedAt");
          final BookmarkEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpAffairId;
            _tmpAffairId = _cursor.getString(_cursorIndexOfAffairId);
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
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpBookmarkedAt;
            _tmpBookmarkedAt = _cursor.getLong(_cursorIndexOfBookmarkedAt);
            _result = new BookmarkEntity(_tmpAffairId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpTimestamp,_tmpBookmarkedAt);
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
  public Flow<List<BookmarkEntity>> searchBookmarks(final String query) {
    final String _sql = "SELECT * FROM bookmarks WHERE titleEnglish LIKE '%' || ? || '%' OR titleHindi LIKE '%' || ? || '%' ORDER BY bookmarkedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAffairId = CursorUtil.getColumnIndexOrThrow(_cursor, "affairId");
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
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfBookmarkedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkedAt");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final String _tmpAffairId;
            _tmpAffairId = _cursor.getString(_cursorIndexOfAffairId);
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
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpBookmarkedAt;
            _tmpBookmarkedAt = _cursor.getLong(_cursorIndexOfBookmarkedAt);
            _item = new BookmarkEntity(_tmpAffairId,_tmpTitleEnglish,_tmpTitleHindi,_tmpKeyPointsEnglish,_tmpKeyPointsHindi,_tmpImageUrl,_tmpCategory,_tmpSourceUrl,_tmpSourceName,_tmpRelevanceScore,_tmpDate,_tmpTimestamp,_tmpBookmarkedAt);
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
  public Object getBookmarkCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM bookmarks";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
  public Flow<List<String>> getAllBookmarkDates() {
    final String _sql = "SELECT DISTINCT date FROM bookmarks ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<String>>() {
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
