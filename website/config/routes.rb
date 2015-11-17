Rails.application.routes.draw do

  get 'search' => 'searchrios#display'
  get 'profile' => 'profile#display'
  get 'home' => 'home#homepage'
  get 'contactos' => 'about#about'
  get 'documentos' => 'documentos_relacionados#documentos'

  get 'concelhos' => 'concelho#getConcelhosFromDistrito'


  resources :guardarios, only: [:index, :show, :new, :create, :destroy]
  resources :reports, only: [:index, :show, :new, :create, :destroy]
  resources :form_irrs
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  devise_for :users, :controllers => {registrations: 'registrations'}

  devise_scope :user do
    get '/users/sign_out' => 'devise/sessions#destroy'
  end

  root 'home#homepage'

  # You can have the root of your site routed with "root"


  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end


  namespace :api do
    namespace :v1 do
      resources :problems
      resources :services
      devise_scope :user do
        post "/sign_in", :to => 'sessions#create'
        post "/sign_up", :to => 'registrations#create'
        delete "/sign_out", :to => 'sessions#destroy'
      end
    end
  end

  namespace :api do
    namespace :v2 do
      post "/form_irrs", :to => 'form_irrs#create'
    end
  end

end
