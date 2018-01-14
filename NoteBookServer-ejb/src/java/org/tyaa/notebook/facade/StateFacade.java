/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.notebook.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import org.tyaa.notebook.entity.State;

/**
 *
 * @author Администратор
 */
@Stateless
public class StateFacade extends AbstractFacade<State> {

    @PersistenceContext(unitName = "NoteBookServer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StateFacade() {
        super(State.class);
    }
    
    //Метод, который принимает название состояния
    //и возвращает объект этого состояния, если
    //соответствующая строка найдена в базе
    public State getStateByName(String _name){
    
        //Получаем от управляющего объект-строитель запросов к БД
        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        //Создаем запрос первого рода, с помощью которого удобно конструировать
        javax.persistence.criteria.CriteriaQuery cq =
                criteriaBuilder.createQuery();
        //Создаем корень запроса: получать данные из таблицы State
        javax.persistence.criteria.Root<State> rt =
                cq.from(State.class);
        //Уточняем: только такие строки, у которых значение в колонке name
        //равно значению аргумента, с которым вызвали метод getStateByName: _name
        cq.where(criteriaBuilder.equal(rt.get("name"), _name));
        //Создаем запрос второго рода, который можно выполнить
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        //Выполняем запрос второго рода, получаем данные искомой строки
        //в виде java-объекта и приводим его к типу State
        return ((State) q.getSingleResult());
    }
}
