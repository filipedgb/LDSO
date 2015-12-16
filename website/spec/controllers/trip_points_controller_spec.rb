require 'spec_helper'

# This spec was generated by rspec-rails when you ran the scaffold generator.
# It demonstrates how one might use RSpec to specify the controller code that
# was generated by Rails when you ran the scaffold generator.
#
# It assumes that the implementation code is generated by the rails scaffold
# generator.  If you are using any extension libraries to generate different
# controller code, this generated spec may or may not pass.
#
# It only uses APIs available in rails and/or rspec-rails.  There are a number
# of tools you can use to make these specs even more expressive, but we're
# sticking to rails and rspec-rails APIs to keep things simple and stable.
#
# Compared to earlier versions of this generator, there is very limited use of
# stubs and message expectations in this spec.  Stubs are only used when there
# is no simpler way to get a handle on the object needed for the example.
# Message expectations are only used when there is no simpler way to specify
# that an instance is receiving a specific message.

describe TripPointsController do

  # This should return the minimal set of attributes required to create a valid
  # TripPoint. As you add validations to TripPoint, be sure to
  # adjust the attributes here as well.
  let(:valid_attributes) { {  } }

  # This should return the minimal set of values that should be in the session
  # in order to pass any filters (e.g. authentication) defined in
  # TripPointsController. Be sure to keep this updated too.
  let(:valid_session) { {} }

  describe "GET index" do
    it "assigns all trip_points as @trip_points" do
      trip_point = TripPoint.create! valid_attributes
      get :index, {}, valid_session
      assigns(:trip_points).should eq([trip_point])
    end
  end

  describe "GET show" do
    it "assigns the requested trip_point as @trip_point" do
      trip_point = TripPoint.create! valid_attributes
      get :show, {:id => trip_point.to_param}, valid_session
      assigns(:trip_point).should eq(trip_point)
    end
  end

  describe "GET new" do
    it "assigns a new trip_point as @trip_point" do
      get :new, {}, valid_session
      assigns(:trip_point).should be_a_new(TripPoint)
    end
  end

  describe "GET edit" do
    it "assigns the requested trip_point as @trip_point" do
      trip_point = TripPoint.create! valid_attributes
      get :edit, {:id => trip_point.to_param}, valid_session
      assigns(:trip_point).should eq(trip_point)
    end
  end

  describe "POST create" do
    describe "with valid params" do
      it "creates a new TripPoint" do
        expect {
          post :create, {:trip_point => valid_attributes}, valid_session
        }.to change(TripPoint, :count).by(1)
      end

      it "assigns a newly created trip_point as @trip_point" do
        post :create, {:trip_point => valid_attributes}, valid_session
        assigns(:trip_point).should be_a(TripPoint)
        assigns(:trip_point).should be_persisted
      end

      it "redirects to the created trip_point" do
        post :create, {:trip_point => valid_attributes}, valid_session
        response.should redirect_to(TripPoint.last)
      end
    end

    describe "with invalid params" do
      it "assigns a newly created but unsaved trip_point as @trip_point" do
        # Trigger the behavior that occurs when invalid params are submitted
        TripPoint.any_instance.stub(:save).and_return(false)
        post :create, {:trip_point => {  }}, valid_session
        assigns(:trip_point).should be_a_new(TripPoint)
      end

      it "re-renders the 'new' template" do
        # Trigger the behavior that occurs when invalid params are submitted
        TripPoint.any_instance.stub(:save).and_return(false)
        post :create, {:trip_point => {  }}, valid_session
        response.should render_template("new")
      end
    end
  end

  describe "PUT update" do
    describe "with valid params" do
      it "updates the requested trip_point" do
        trip_point = TripPoint.create! valid_attributes
        # Assuming there are no other trip_points in the database, this
        # specifies that the TripPoint created on the previous line
        # receives the :update_attributes message with whatever params are
        # submitted in the request.
        TripPoint.any_instance.should_receive(:update).with({ "these" => "params" })
        put :update, {:id => trip_point.to_param, :trip_point => { "these" => "params" }}, valid_session
      end

      it "assigns the requested trip_point as @trip_point" do
        trip_point = TripPoint.create! valid_attributes
        put :update, {:id => trip_point.to_param, :trip_point => valid_attributes}, valid_session
        assigns(:trip_point).should eq(trip_point)
      end

      it "redirects to the trip_point" do
        trip_point = TripPoint.create! valid_attributes
        put :update, {:id => trip_point.to_param, :trip_point => valid_attributes}, valid_session
        response.should redirect_to(trip_point)
      end
    end

    describe "with invalid params" do
      it "assigns the trip_point as @trip_point" do
        trip_point = TripPoint.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        TripPoint.any_instance.stub(:save).and_return(false)
        put :update, {:id => trip_point.to_param, :trip_point => {  }}, valid_session
        assigns(:trip_point).should eq(trip_point)
      end

      it "re-renders the 'edit' template" do
        trip_point = TripPoint.create! valid_attributes
        # Trigger the behavior that occurs when invalid params are submitted
        TripPoint.any_instance.stub(:save).and_return(false)
        put :update, {:id => trip_point.to_param, :trip_point => {  }}, valid_session
        response.should render_template("edit")
      end
    end
  end

  describe "DELETE destroy" do
    it "destroys the requested trip_point" do
      trip_point = TripPoint.create! valid_attributes
      expect {
        delete :destroy, {:id => trip_point.to_param}, valid_session
      }.to change(TripPoint, :count).by(-1)
    end

    it "redirects to the trip_points list" do
      trip_point = TripPoint.create! valid_attributes
      delete :destroy, {:id => trip_point.to_param}, valid_session
      response.should redirect_to(trip_points_url)
    end
  end

end
