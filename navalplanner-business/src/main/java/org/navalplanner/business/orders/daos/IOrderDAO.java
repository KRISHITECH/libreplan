/*
 * This file is part of NavalPlan
 *
 * Copyright (C) 2009-2010 Fundación para o Fomento da Calidade Industrial e
 *                         Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.navalplanner.business.orders.daos;

import java.util.Date;
import java.util.List;

import org.navalplanner.business.common.daos.IIntegrationEntityDAO;
import org.navalplanner.business.common.exceptions.InstanceNotFoundException;
import org.navalplanner.business.orders.entities.Order;
import org.navalplanner.business.orders.entities.OrderElement;
import org.navalplanner.business.planner.entities.Task;
import org.navalplanner.business.reports.dtos.OrderCostsPerResourceDTO;
import org.navalplanner.business.resources.entities.Criterion;
import org.navalplanner.business.scenarios.entities.Scenario;
import org.navalplanner.business.users.entities.User;

/**
 * Contract for {@link OrderDAO}
 * @author Óscar González Fernández <ogonzalez@igalia.com>
 * @author Lorenzo Tilve Álvaro <ltilve@igalia.com>
 * @author Diego Pino Garcia <dpino@igalia.com>
 * @author Jacobo Aragunde Pérez <jaragunde@igalia.com>
 */
public interface IOrderDAO extends IIntegrationEntityDAO<Order> {

    /**
     * Gets all the orders.
     * @return A {@link List} of {@link Order} objects
     */
    List<Order> getOrders();

    /**
     * Builds contents for OrderCostsPerResource report
     * @return A {@link List} of {@link OrderCostsPerResourceDTO} objects for
     * reporting
     */
    List<OrderCostsPerResourceDTO> getOrderCostsPerResource(List<Order> orders,
            Date startingDate, Date endingDate,
            List<Criterion> criterions);

    /**
     * Returns a list of orders filtered by the read authorizations of the indicated
     * user. Write authorizations are also counted, because they implicitly suppose
     * read access.
     * @param user User.
     * @return Filtered list of orders.
     */
    List<Order> getOrdersByReadAuthorization(User user);

    /**
     * Returns a list of orders filtered by the write authorizations of the indicated
     * user.
     * @param user User.
     * @return Filtered list of orders.
     */
    List<Order> getOrdersByWriteAuthorization(User user);

    List<Order> getOrdersByReadAuthorizationByScenario(User user,
            Scenario scenario);

    /**
     * Returns the order filtered by the name. If name is blank (whitespace,
     * empty ("") or null, it throws <code>InstanceNotFoundException</code>.
     * @param name
     *            String
     * @return order Order
     */
    public Order findByNameAnotherTransaction(String name)
            throws InstanceNotFoundException;

    List<Order> getOrdersByScenario(Scenario scenario);

    List<Task> getFilteredTask(List<OrderElement> orderElements,
            List<Criterion> criterions);
}
