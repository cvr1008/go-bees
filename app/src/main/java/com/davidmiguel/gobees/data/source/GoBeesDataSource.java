package com.davidmiguel.gobees.data.source;

import android.support.annotation.NonNull;

import com.davidmiguel.gobees.data.model.Apiary;
import com.davidmiguel.gobees.data.model.Hive;
import com.davidmiguel.gobees.data.model.Record;
import com.davidmiguel.gobees.data.model.Recording;

import java.util.Date;
import java.util.List;

/**
 * Main entry point for accessing apiaries data.
 */
public interface GoBeesDataSource {

    /**
     * Opens database.
     */
    void openDb();

    /**
     * Closes database.
     */
    void closeDb();

    /**
     * Clean database.
     */
    void deleteAll();

    /**
     * Gets all apiaries.
     * Note: don't modify the Apiary objects.
     *
     * @param callback GetApiariesCallback.
     */
    void getApiaries(@NonNull GetApiariesCallback callback);

    /**
     * Gets apiary with given id.
     * Note: don't modify the Apiary object.
     *
     * @param apiaryId apiary id.
     * @param callback GetApiaryCallback
     */
    void getApiary(long apiaryId, @NonNull GetApiaryCallback callback);

    /**
     * Saves given apiary. If it already exists, is updated.
     * Note: apiary must be a new unmanaged object (don't modify managed objects).
     *
     * @param apiary   apiary unmanaged object.
     * @param callback TaskCallback.
     */
    void saveApiary(@NonNull Apiary apiary, @NonNull TaskCallback callback);

    /**
     * Force to update cache.
     */
    void refreshApiaries();

    /**
     * Delete apiary.
     *
     * @param apiaryId apiary id.
     * @param callback TaskCallback.
     */
    void deleteApiary(long apiaryId, @NonNull TaskCallback callback);

    /**
     * Delete all apiaries.
     *
     * @param callback TaskCallback.
     */
    void deleteAllApiaries(@NonNull TaskCallback callback);

    /**
     * Returns the next apiary id.
     * (Realm does not support auto-increment at the moment).
     *
     * @param callback GetNextApiaryIdCallback.
     */
    void getNextApiaryId(@NonNull GetNextApiaryIdCallback callback);

    /**
     * Gets all hives.
     * Note: don't modify the Hive objects.
     *
     * @param apiaryId apiary id.
     * @param callback GetHivesCallback.
     */
    void getHives(long apiaryId, @NonNull GetHivesCallback callback);

    /**
     * Gets hive with given id.
     * Note: don't modify the Hive object.
     *
     * @param hiveId   hive id.
     * @param callback GetHiveCallback
     */
    void getHive(long hiveId, @NonNull GetHiveCallback callback);

    /**
     * Returns a hive with all its recordings (but no meteo data).
     *
     * @param hiveId   hive id.
     * @param callback GetHiveCallback.
     */
    void getHiveWithRecordings(long hiveId, @NonNull GetHiveCallback callback);

    /**
     * Force to update hives cache.
     */
    void refreshHives(long apiaryId);

    /**
     * Saves given hive. If it already exists, is updated.
     * Note: hive must be a new unmanaged object (don't modify managed objects).
     *
     * @param apiaryId apiary id.
     * @param hive     hive unmanaged object.
     * @param callback TaskCallback.
     */
    void saveHive(long apiaryId, @NonNull Hive hive, @NonNull TaskCallback callback);

    /**
     * Returns the next hive id.
     * (Realm does not support auto-increment at the moment).
     *
     * @param callback GetNextHiveIdCallback.
     */
    void getNextHiveId(@NonNull GetNextHiveIdCallback callback);

    /**
     * Saves given record. If it already exists, is updated.
     * Note: record must be a new unmanaged object (don't modify managed objects).
     *
     * @param hiveId   hive id.
     * @param record   record unmanaged object.
     * @param callback TaskCallback.
     */
    void saveRecord(long hiveId, @NonNull Record record, @NonNull TaskCallback callback);

    /**
     * Saves given list of records.
     * Note: record must be a new unmanaged object (don't modify managed objects).
     *
     * @param records  list of record unmanaged objects.
     * @param callback TaskCallback.
     */
    void saveRecords(long hiveId, @NonNull List<Record> records, @NonNull TaskCallback callback);

    /**
     * Get recording with records of given period.
     *
     * @param hiveId   hive id.
     * @param start    start of the period (00:00 of that date).
     * @param end      end of the period (23:59 of that date).
     * @param callback GetRecordingCallback.
     */
    void getRecording(long hiveId, Date start, Date end, @NonNull GetRecordingCallback callback);

    /**
     * Force to update recordings cache.
     */
    void refreshRecordings(long hiveId);

    interface GetApiariesCallback {
        void onApiariesLoaded(List<Apiary> apiaries);

        void onDataNotAvailable();
    }

    interface GetApiaryCallback {
        void onApiaryLoaded(Apiary apiary);

        void onDataNotAvailable();
    }

    interface GetNextApiaryIdCallback {
        void onNextApiaryIdLoaded(long apiaryId);
    }

    interface GetHivesCallback {
        void onHivesLoaded(List<Hive> hives);

        void onDataNotAvailable();
    }

    interface GetHiveCallback {
        void onHiveLoaded(Hive hive);

        void onDataNotAvailable();
    }

    interface GetNextHiveIdCallback {
        void onNextHiveIdLoaded(long hiveId);
    }

    interface GetRecordingCallback {
        void onRecordingLoaded(Recording recording);

        void onDataNotAvailable();
    }

    interface TaskCallback {
        void onSuccess();

        void onFailure();
    }
}