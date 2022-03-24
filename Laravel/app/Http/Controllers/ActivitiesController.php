<?php

namespace App\Http\Controllers;

use App\Models\activity;
use Illuminate\Http\Request;
use App\Http\Requests\ActivitiesRequest;

class ActivitiesController extends Controller
{
    public function getActivities($id){

        $activities = activity::where('idClub','=',$id)->orderBy('updated_at')->paginate(5);
        if (sizeof($activities) > 0)
            return response()->json(
                $activities,
                200
            );
        else
            return response()->json([
                "Aucun activité"
            ], 404);
    }
    public function createActivities($id, ActivitiesRequest $request){
        $paragraphe = $request->input('paragraphe');
        $idClub = $id;

        $newActivity = array(
            "paragraphe" => $paragraphe,
            "idClub" => $idClub
        );

        $createNewActivity = activity::create($newActivity);

        if ($createNewActivity) {
            return response()->json([
                'data' => [
                    'message' => 'Activity created',
                    'id' => $createNewActivity->id,
                    'attributes' => $createNewActivity
                ]
            ], 201);
        }
        return "Activity created";
    }

    public function deleteActivity($idClub,$idActivity){
      
        $activity = activity::find($idActivity);
       
        if ($activity){
            $activity->delete();
            return response()->json([
                'type' => 'Activity',
                'message' => 'Activity Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Activity',
                'message' => 'Activity non trouvée'
            ], 404);
        }
    }

    public function updateActivity($idClub, $idActivity, ActivitiesRequest $request){

        $activity = activity::find($idActivity);
        if ($activity) {
      
            $activity->paragraphe = $request->input('paragraphe') ? $request->input('paragraphe') : $activity->paragraphe;
            $activity->idClub = $idClub;
            $activity->save();
            return response()->json([
                'message' => 'Activity mis à jour',
                'id' => $activity->id,
                'attributes' => $activity
            ], 201);
        } else {
            return response()->json([
                "Activity non trouvée"
            ], 404);
        }
    }
}
