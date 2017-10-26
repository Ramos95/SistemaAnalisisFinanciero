/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.anf135.datos.acceso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author zywel
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean crear(T entity) {
        boolean salida = false;
        T e = create(entity);

        if (e != null) {
            salida = true;
        }

        return salida;
    }

    public T create(T entity) {
        T salida = null;

        try {
            EntityManager em = getEntityManager();
            if (em != null && entity != null) {
                em.persist(entity);
                salida = entity;
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return salida;
    }

    public boolean editar(T entity) {
        boolean salida = false;

        T e = edit(entity);
        if (e != null) {
            salida = true;
        }

        return salida;
    }

    public T edit(T entity) {
        T salida = null;

        try {
            EntityManager em = this.getEntityManager();
            if (em != null && entity != null) {
                em.merge(entity);
                salida = entity;
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return salida;
    }

    public boolean eliminar(T entity) {
        boolean salida = false;

        T e = remove(entity);
        if (e != null) {
            salida = true;
        }

        return salida;
    }

    public T remove(T entity) {
        T salida = null;

        try {
            EntityManager em = this.getEntityManager();
            if (em != null && entity != null) {
                em.remove(em.merge(entity));
                salida = entity;
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return salida;
    }

    public T find(Object id) {
        if (getEntityManager() != null) {
            return getEntityManager().find(entityClass, id);
        }

        return null;
    }

    public List<T> findAll() {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public List<T> findRange(int[] range) {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1); //first + pageSize - first + 1 = (pageSize + 1)
            q.setFirstResult(range[0]); //first
            return q.getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public List<T> findRango(int desde, int tamanioPagina) {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(rt);
            //cq.orderBy(cb.asc(rt.get("nombre")));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(tamanioPagina);
            q.setFirstResult(desde);
            return q.getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public List<T> findRango(int desde, int tamanioPagina, String orderField) {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(rt);
            cq.orderBy(cb.asc(rt.get(orderField))); //nuevo byOrderField
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(tamanioPagina);
            q.setFirstResult(desde);
            return q.getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public int count() {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }

        return 0;
    }

    public List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina) {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();

            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(rt);

            boolean contieneId = false;

            List<Predicate> ps = new ArrayList<>();

            //Recorriendo el map filtros
            for (Map.Entry<String, Object> e : filtros.entrySet()) {
                String key = e.getKey();
                Object val = e.getValue();

                try {
                    //Comparando si el campo es @Id
                    if (entityClass.getDeclaredField(key).isAnnotationPresent(Id.class)) {
                        contieneId = true;

                        //Long.parseLong(val.toString());
                        Predicate p = cb.equal(rt.get(key), val);
                        cq.where(p);
                        break;
                    } else {
                        ps.add(cb.like(cb.lower(rt.<String>get(key)), "%" + val + "%"));
                    }
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    System.out.println("Ha ingresado tipo incorrecto de dato 2");
                    return new ArrayList<T>() {
                    };
                }
            }

            if (!contieneId) {
                cq.where(cb.and(ps.toArray(new Predicate[]{})));
            }

            //cq.orderBy(cb.asc(rt.get("nombre")));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(tamanioPagina);
            q.setFirstResult(desde);

            return q.getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public List<T> findByDataFilters(Map<String, Object> filtros, int desde, int tamanioPagina, String orderField) {
        if (getEntityManager() != null) {
            javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();

            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(rt);

            boolean contieneId = false;

            List<Predicate> ps = new ArrayList<>();

            //Recorriendo el map filtros
            for (Map.Entry<String, Object> e : filtros.entrySet()) {
                String key = e.getKey();
                Object val = e.getValue();

                try {
                    //Comparando si el campo es @Id
                    if (entityClass.getDeclaredField(key).isAnnotationPresent(Id.class)) {
                        contieneId = true;

                        //Long.parseLong(val.toString());
                        Predicate p = cb.equal(rt.get(key), val);
                        cq.where(p);
                        break;
                    } else {
                        ps.add(cb.like(cb.lower(rt.<String>get(key)), "%" + val + "%"));
                    }
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    System.out.println("Ha ingresado tipo incorrecto de dato 3");
                    return new ArrayList<T>() {
                    };
                }
            }

            if (!contieneId) {
                cq.where(cb.and(ps.toArray(new Predicate[]{})));
            }

            cq.orderBy(cb.asc(rt.get(orderField))); //nuevo

            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(tamanioPagina);
            q.setFirstResult(desde);

            return q.getResultList();
        }

        return new ArrayList<T>() {
        };
    }

    public int countByDataFilters(Map<String, Object> filtros) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();

        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(cb.count(rt));

        boolean contieneId = false;

        List<Predicate> ps = new ArrayList<>();

        //Recorriendo el map filtros
        for (Map.Entry<String, Object> e : filtros.entrySet()) {
            String key = e.getKey();
            String val = e.getValue().toString().toLowerCase();

            try {
                //Comparando si el campo es @Id
                if (entityClass.getDeclaredField(key).isAnnotationPresent(Id.class)) {
                    contieneId = true;
                    //Long.parseLong(val);
                    Predicate p = cb.equal(rt.get(key), val);
                    cq.where(p);
                    break;
                } else {
                    ps.add(cb.like(cb.lower(rt.<String>get(key)), "%" + val + "%"));
                }
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(AbstractFacade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
                System.out.println("Ha ingresado tipo Incorrecto de dato 4");
                return 0;
            }
        }

        if (!contieneId) {
            cq.where(cb.and(ps.toArray(new Predicate[]{})));
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }
}
