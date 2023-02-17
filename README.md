## 6. Multithreading
----
### Materials

[Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
[L10 from slide 24](https://drive.google.com/file/d/1lQorg4OeGddgLf54a3NaSKCe3NbPKWXN/view?usp=sharing) 
[L11 from](https://drive.google.com/file/d/1hK3FwY2zJG0ChY3phqA2UlqJW15lZf\_O/view?usp=sharing) 

### VideoLectures
- [Multithreading, part 1](https://youtu.be/fH7Yb9HnK3Q)
- [ Multithreading, part 2](https://youtu.be/RxrtmHPnOo0)

### Task #6

Please implement `create order` functionality. Each order should be processed in separate thread. Whe user select product
, generate the random int from 1 to 30, and create thread that will process selected order for selected time, and after it
place the product in another collection (for example, purchased goods).
And create one more thread, that will be executed periodically, e.g. ones in 2 mins, that will clean up purchased collection.

You can implement this in "native" java methods but better and simplier to use java.util.concurrent package.  
