<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Validation\ValidationException;
use App\Models\avis;
use App\Models\User;
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
        //  $user = auth()->user();
        //  dump($user);
        $avis=avis::get();
        return $avis;
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

        // $validator = Validator::make($request->all(), [
        //     'title' => 'required',
        //     'content'=>'required',
        //     'image' => 'require',
        //  ]);
     
$user = $request->user();
        $avis= new avis();

            $avis->title=$request->get('title');
            $avis->content=$request->get('content');
            $avis->image= $request->get('image');
            $avis->from_id=$user->id;

            // $avis->image=$request->file('image')->hashName();
            $avis->save();
        
 
        //return redirect()->route('/listavis');

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
        $avis->image= $request->image;
        dd($request->image);
        $avis->save();
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
        $avis->delete();
    }
}
