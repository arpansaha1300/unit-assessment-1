import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom'

import DefaultLayout from '~/layouts/default'

const Home = () => import('../pages/index.jsx')
const Details = () => import('../pages/details.jsx')

const routes = createRoutesFromElements(
  <Route element={<DefaultLayout />}>
    <Route path="/" lazy={Home} />
    <Route path="/:releaseId" lazy={Details} />
  </Route>
)

const router = createBrowserRouter(routes)

export default function AppRoutes() {
  return <RouterProvider router={router} />
}
