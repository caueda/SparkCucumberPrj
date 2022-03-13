package threads;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.*;

@Slf4j
public class WhatchFolderModification {
    public static void main(String[] args) throws Exception {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        Path path = Paths.get("c:/Java/temp");

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }
}
