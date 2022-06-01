<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Validation\ValidationException;
use App\Models\avis;
use App\Models\User;
use App\Models\Role;
use App\Models\RoleUser;
use Validator;

class posteavisController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
          $user = auth()->user();

        $avis=avis::get();
        if($avis!=null)
        {
            for($i=0;$i<count($avis);$i++){
           $avis[$i]->from_id=$user->name;
        }
        return $avis;
    }
       
        
       return "no data found";
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

        $user = $request->user();
        $avis= new avis();

            $avis->title=$request->get('title');
            $avis->content=$request->get('content');
            $avis->image= $request->get('image');
            $avis->from_id=$user->id;

            // $avis->image=$request->file('image')->hashName();
            if($avis->title!=null && $avis->content!=null && $avis->image!=null)
            {
                $avis->save();
            }
            else{
                return response()->json([
                 'data' => [
                'message' => 'all Fields required',
                    ]
             ], 404);
               
            }
        


    }
 
    public function searchUser($id){
        $data = users::find($id);
        return $data;
    }
    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $data = avis::find($id);
        $user = auth()->user();
        $role_user=
        $data->from_id=$user->name;
        return $data;
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $avis = avis::find($id);
        $avis->title=$request->get('title');
        $avis->content=$request->get('content');
       $avis->image=$request->image;
      
        if($avis->title!=null && $avis->content!=null && $avis->image!=null)
        {
            $avis->save();
        }
        else{
            return response()->json([
             'data' => [
            'message' => 'all Fields required',
                ]
         ], 404);
           
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $avis = avis::find($id);
        $role_user=RoleUser::where('user_id','=',$avis->from_id);
        $role=Role::find($role_user->role_id);
        dd($role->id);
        // $avis->delete();
    }
}
