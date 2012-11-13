package ru.spbstu.telematics.student_Vasilevsky.lab02;

/**
 * Коллекция хранит данные в структуре Red-black tree и гарантирует 
 * логарифмическое время вставки, удаления и поиска.
 * 
 * 
 */
public interface IRedBlackTree<T extends Comparable<T>> {
  /**
   * Добавить элемент в дерево
   * @param e
   */
  void add(T e);
  /**
   * Удалить элемент из дерева
   */
  boolean remove(T o);
  /**
   * Возвращает true, если элемент содержится в дереве
   */
  boolean contains(T o);
}
